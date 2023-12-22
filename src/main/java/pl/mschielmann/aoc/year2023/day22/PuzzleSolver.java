package pl.mschielmann.aoc.year2023.day22;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;

@Slf4j
class PuzzleSolver
{
    private final String puzzleInput;

    PuzzleSolver(String puzzleInput)
    {
        this.puzzleInput = puzzleInput;
    }

    long solvePartOne()
    {
        List<Rock> fallingRocks = puzzleInput.lines().map(Rock::createUsing).sorted().toList();
        List<Rock> positionedRocks = letRocksFall(fallingRocks);

        long result = 0;
        for (Rock rock : positionedRocks)
        {
            boolean canBeRemoved = true;
            for (Rock rockOnTop : findLayingOnTopOf(rock, positionedRocks))
            {
                if (doesNotHaveAtLeastOneOtherSupportingRocks(rockOnTop, rock, positionedRocks))
                {
                    canBeRemoved = false;
                    break;
                }
            }
            if (canBeRemoved)
            {
                result++;
            }
        }
        return result;
    }

    long solvePartTwo()
    {
        List<Rock> fallingRocks = puzzleInput.lines().map(Rock::createUsing).sorted().toList();
        List<Rock> positionedRocks = letRocksFall(fallingRocks);

        long result = 0;
        for (Rock rock : positionedRocks)
        {
            List<Rock> setupCopied = new ArrayList<>(positionedRocks);
            takeOutRock(rock, setupCopied);
            result += (positionedRocks.size() - (setupCopied.size() + 1));
        }

        return result;
    }

    private boolean doesNotHaveAtLeastOneOtherSupportingRocks(Rock rockToTest, Rock supportingRock, List<Rock> positionedRocks)
    {
        Rock rockIfMovedDown = rockToTest.moveDown();
        return positionedRocks.stream()
                .filter(positionedRock -> !positionedRock.equals(rockToTest))
                .filter(positionedRock -> !positionedRock.equals(supportingRock))
                .noneMatch(positionedRock -> positionedRock.hasSamePositionInSpaceAs(rockIfMovedDown));
    }

    private void takeOutRock(Rock rock, List<Rock> positionedRocks)
    {
        positionedRocks.remove(rock);
        for (Rock rockOnTop : findLayingOnTopOf(rock, positionedRocks))
        {
            if (doesNotHaveAtLeastOneOtherSupportingRocks(rockOnTop, rock, positionedRocks))
            {
                takeOutRock(rockOnTop, positionedRocks);
            }
        }
    }

    private List<Rock> letRocksFall(List<Rock> fallingRocks)
    {
        List<Rock> positionedRocks = new ArrayList<>();

        for (Rock rock : fallingRocks)
        {
            Rock rockWithPreviousPosition = rock;
            Rock rockWithNewPosition = rock;
            while (rockWithNewPosition.position().z() > 0 && newPositionNotInConflictWith(positionedRocks, rockWithNewPosition))
            {
                rockWithPreviousPosition = rockWithNewPosition;
                rockWithNewPosition = rockWithNewPosition.moveDown();
            }
            positionedRocks.add(rockWithPreviousPosition);
        }
        return positionedRocks;
    }

    private List<Rock> findLayingOnTopOf(Rock rock, List<Rock> positionedRocks)
    {
        Rock rockIfMovedUp = new Rock(rock.shape(), new Coordinates(rock.position().x(), rock.position().y(), rock.position().z() + 1));
        return positionedRocks.stream()
                .filter(positionedRock -> !positionedRock.equals(rock))
                .filter(positionedRock -> positionedRock.hasSamePositionInSpaceAs(rockIfMovedUp))
                .toList();
    }

    private boolean newPositionNotInConflictWith(List<Rock> positionedRocks, Rock rockWithNewPosition)
    {
        return positionedRocks.stream().noneMatch(rockWithNewPosition::hasSamePositionInSpaceAs);
    }

    private record Rock(Shape shape, Coordinates position) implements Comparable<Rock>
    {

        @Override
        public String toString()
        {
            return positionInSpace().toString();
        }

        @Override
        public int compareTo(Rock o)
        {
            return Integer.compare(this.position.z, o.position.z);
        }

        private static Rock createUsing(String definition)
        {
            List<Coordinates> corners = List.of(Coordinates.fromString(definition.split("~")[0]),
                    Coordinates.fromString(definition.split("~")[1]));

            Coordinates nearestCornerCoordinates = corners.stream().sorted().findFirst().orElseThrow();
            Coordinates furthestCoordinates = corners.stream().max(Comparator.naturalOrder()).orElseThrow();

            Shape shape = Shape.createFor(nearestCornerCoordinates, furthestCoordinates);

            return new Rock(shape, nearestCornerCoordinates);
        }

        public boolean hasSamePositionInSpaceAs(Rock otherRock)
        {
            return positionInSpace().stream().anyMatch(position -> otherRock.positionInSpace().contains(position));
        }

        private List<Coordinates> positionInSpace()
        {
            return shape.coordinatesTaken()
                    .stream()
                    .map(shapeRelativeCoordinates -> new Coordinates(shapeRelativeCoordinates.x() + position.x(), shapeRelativeCoordinates.y() + position.y(), shapeRelativeCoordinates.z + position.z()))
                    .toList();
        }

        public Rock moveDown()
        {
            return new Rock(shape, new Coordinates(position().x(), position().y(), position().z() - 1));
        }
    }

    private record Coordinates(int x, int y, int z) implements Comparable<Coordinates>
    {
        private static Coordinates fromString(String input)
        {
            String[] coordinatesAsString = input.split(",");
            return new Coordinates(parseInt(coordinatesAsString[0]),
                    parseInt(coordinatesAsString[1]),
                    parseInt(coordinatesAsString[2]));
        }

        @Override
        public int compareTo(Coordinates other)
        {
            int result = Integer.compare(this.z, other.z);
            if (result != 0)
            {
                return result;
            }
            result = Integer.compare(this.y, other.y);
            if (result != 0)
            {
                return result;
            }
            return Integer.compare(this.x, other.x);
        }
    }

    private record Shape(List<Coordinates> coordinatesTaken)
    {
        private static Shape createFor(Coordinates coordinates1, Coordinates coordinates2)
        {
            List<Coordinates> coordinates = new ArrayList<>();
            for (int zIndex = 0; zIndex <= coordinates2.z() - coordinates1.z(); zIndex++)
            {
                for (int yIndex = 0; yIndex <= coordinates2.y() - coordinates1.y(); yIndex++)
                {
                    for (int xIndex = 0; xIndex <= coordinates2.x() - coordinates1.x(); xIndex++)
                    {
                        coordinates.add(new Coordinates(xIndex, yIndex, zIndex));
                    }
                }
            }
            return new Shape(coordinates);
        }
    }
}

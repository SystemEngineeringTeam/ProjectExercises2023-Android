package net.harutiro.majanhartrate.Utils

import net.harutiro.majanhartrate.Entity.Direction

class DirectionUtils {
    companion object{
        fun direction2String(direction: Direction): String {
            return when (direction) {
                Direction.EAST -> "東"
                Direction.SOUTH -> "南"
                Direction.WEST -> "西"
                Direction.NORTH -> "北"
            }
        }

        fun string2Direction(string: String): Direction {
            return when (string) {
                "東" -> Direction.EAST
                "南" -> Direction.SOUTH
                "西" -> Direction.WEST
                "北" -> Direction.NORTH
                else -> Direction.EAST
            }
        }
    }
}
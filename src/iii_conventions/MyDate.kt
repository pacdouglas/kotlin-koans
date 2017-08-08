package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    operator fun plus(timeIntervals: RepeatedTimeInterval): MyDate {
        return addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)
    }

    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}
class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate>{
    override fun iterator(): Iterator<MyDate> {
        return DateIterator()
    }

    operator fun contains(date: MyDate): Boolean {
        return start <= date && date <= endInclusive
    }
}

class DateIterator: Iterator<MyDate> {
    override fun next(): MyDate {
        return MyDate(1970, 1, 1)
    }

    override fun hasNext(): Boolean {
        return false
    }
}

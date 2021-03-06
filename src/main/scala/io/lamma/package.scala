package io

import io.lamma.Locator.Last

/**
 * <b>Lamma schedule generator</b> is a professional financial schedule generation library. <br>
 * <br>
 * Some use cases are:
 * <ol>
 *   <li>mortgage repayment schedule generation</li>
 *   <li>fixed income coupon payment schedule generation</li>
 *   <li>equity derivative fixing date generation</li>
 *   <li>..etc..</li>
 * </ol>
 *
 * <br>
 * The starting point of the libary is the {@link com.lamma.Lamma} class
 *
 * @see <a href="http://www.lamma.io" target="_blank">http://www.lamma.io</a> for samples and tutorials
 */
package object lamma {
  // ========== day of week ===========
  val Monday = DayOfWeek.MONDAY

  val Tuesday = DayOfWeek.TUESDAY

  val Wednesday = DayOfWeek.WEDNESDAY

  val Thursday = DayOfWeek.THURSDAY

  val Friday = DayOfWeek.FRIDAY

  val Saturday = DayOfWeek.SATURDAY

  val Sunday = DayOfWeek.SUNDAY

  // ========== months ==============
  val January = Month.JANUARY

  val February = Month.FEBRUARY

  val March = Month.MARCH

  val April = Month.APRIL

  val May = Month.MAY

  val June = Month.JUNE

  val July = Month.JULY

  val August = Month.AUGUST

  val September = Month.SEPTEMBER

  val October = Month.OCTOBER

  val November = Month.NOVEMBER

  val December = Month.DECEMBER

  // =========== dates ==============
  implicit def tupleToDate(t: (Int, Int, Int)) = {
    val (yyyy, mm, dd) = t
    Date(yyyy, mm, dd)
  }

  implicit def tuplesToDates(t: ((Int, Int, Int), (Int, Int, Int))) = {
    tupleToDate(t._1) -> tupleToDate(t._2)
  }

  implicit def isoStrToDate(isoStr: String) = Date(isoStr)

  // =========== durations ===============
  val week = 1 week

  val month = 1 month

  val year = 1 year

  implicit class DurationInt(n: Int) {

    def day = DayDuration(n)

    def days = DayDuration(n)

    def week = WeekDuration(n)

    def weeks = WeekDuration(n)

    def month = MonthDuration(n)

    def months = MonthDuration(n)

    def year = YearDuration(n)

    def years = YearDuration(n)
  }

  // ============ patterns ================
  implicit def dayDurationToDailyPattern(d: DayDuration) = Daily(d.n)

  implicit def weekDurationToWeeklyPattern(d: WeekDuration) = Weekly(d.n)

  implicit def monthDurationToMonthlyPattern(d: MonthDuration) = Monthly(d.n)

  implicit def yearDurationToYearlyPattern(d: YearDuration) = Yearly(d.n)

  // =========== locators ======================

  // this is only used as a place holder to complete expression like `10 th day`
  object day

  implicit class LocatorImplicit(n: Int) {
    def st(d: day.type) = th(d)

    def nd(d: day.type) = th(d)

    def rd(d: day.type) = th(d)

    def th(d: day.type) = Locator(n)

    def st(dow: DayOfWeek) = th(dow)

    def nd(dow: DayOfWeek) = th(dow)

    def rd(dow: DayOfWeek) = th(dow)

    def th(dow: DayOfWeek) = Locator(n, dow)
  }

  implicit def weekdayToLocator(weekday: DayOfWeek) = Locator(weekday.n)

  // because of the way scala works
  // we cannot make this like: last day / last Friday
  // http://stackoverflow.com/questions/20163450/scala-does-not-take-parameters-when-chaining-method-calls-without-periods

  val lastDay = Locator(Last)

  val lastMonday = Locator(Last, Monday)

  val lastTuesday = Locator(Last, Tuesday)

  val lastWednesday = Locator(Last, Wednesday)

  val lastThursday = Locator(Last, Thursday)

  val lastFriday = Locator(Last, Friday)

  val lastSaturday = Locator(Last, Saturday)

  val lastSunday = Locator(Last, Sunday)

  implicit def dayOfWeekSupportConversion(dowSupport: DayOfWeekSupport) = dowSupport.dow

  implicit def dayOfMonthSupportConversion(domSupport: DayOfMonthSupport) = domSupport.dom

  implicit def dayOfYearSupportConversion(doySupport: DayOfYearSupport) = doySupport.doy
}
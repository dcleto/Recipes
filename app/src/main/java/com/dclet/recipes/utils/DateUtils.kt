package com.dclet.recipes.utils

import java.util.*

class DateUtils {
    companion object{
        fun getMidNigthInMilis() : Long {
            var date = GregorianCalendar ();
            date.set(Calendar.HOUR_OF_DAY, 0);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
            return date.timeInMillis
        }
    }
}
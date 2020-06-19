package study.junghoon.video.clientapp.util

class TimeConverter {

    companion object {
        fun millisecondToTime(milliSec: String): String {
            val millisecond = milliSec.toLong()

            val minutes = millisecond / 1000 / 60
            val seconds = millisecond / 1000 % 60

            var secondsStr = seconds.toString()
            var minutesStr = minutes.toString()

            if (secondsStr.length == 1) {
                secondsStr = "0$secondsStr"
            }

            if (minutesStr.length == 1) {
                minutesStr = "0$minutesStr"
            }

            return "$minutesStr:$secondsStr"
        }
    }
}
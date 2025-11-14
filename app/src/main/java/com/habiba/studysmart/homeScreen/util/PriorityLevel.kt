package com.habiba.studysmart.homeScreen.util

import androidx.compose.ui.graphics.Color
import com.habiba.studysmart.ui.theme.green
import com.habiba.studysmart.ui.theme.red
import com.habiba.studysmart.ui.theme.yellow

enum class PriorityLevel(val level:String , val color: Color , val value:Int) {
    HIGH("high",red,2),
    MEDIUM("medium",yellow,1),
    LOW("low", green,0);

    companion object {
        //FUNCTION CAN BE USED WITHOUT INSTANTIATING THE CLASS
        // take an integer and return the corresponding priority level
        // if the integer is not in the range of 0 to 2 return medium
        // PriorityLevel.entries iterates over all the objects fro this enum class and return it based on the condition
        fun getPriorityLevel(value :Int)= PriorityLevel.entries.firstOrNull(){it.value == value}?:MEDIUM
        fun getPriorityColor(value:Int):Color{
            return getPriorityLevel(value).color
        }



    }
}
package com.habiba.studysmart.homeScreen.util

enum class InputFieldsErrors(val errorMsg:String) {
    NoError(""),
    EmptySubjectNameField("please enter the subject name"),
    InvalidSubjectName("please enter a valid formate subject name"),
    EmptyGoalHourField("please enter the goal hour"),
    InvalidGoalHourField("please enter a valid goal hour")
}
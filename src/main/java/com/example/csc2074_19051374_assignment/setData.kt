package com.example.csc2074_19051374_assignment

object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val SCORE = "correct_answers"

    fun getQuestions(): MutableList<Question> {
        val questions = mutableListOf<Question>()


        val question1 = Question(
            "Is Melaka River the Longest River in Malaysia?",
            1,
            "True",
            "False",
            2
        )

        questions.add(question1)

        val question2 = Question(
            "Kuala Lumpur is the Capital of Malaysia.",
            2,
            "True",
            "False",
            1
        )

        questions.add(question2)


        val question3 = Question(
            "Malaysia consists of three major races-Malay,Chinese and Indian.",
            3,
            "True",
            "False",
            1
        )

        questions.add(question3)


        val question4 = Question(
            "National animal of Malaysia is Cat.",
            1,
            "True",
            "False",
            2
        )

        questions.add(question4)

        val question5 = Question(
            "Twin Tower is the tallest building in Malaysia.",
            1,
            "True",
            "False",
            2
        )

        questions.add(question5)

        return questions
    }

    var MAX_PROGRESS_VALUE = 5

}

package com.example.workout

class Constants {

    companion object {
        fun defaultExerciseList():ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val basicsquats = ExerciseModel(1,
            "Squats",R.drawable.squatt2,false,false)
            exerciseList.add(basicsquats)

            val bicepcurls = ExerciseModel(2,
                "Bicep Curls",R.drawable.bicepcurls2,false,false)
            exerciseList.add(bicepcurls)

            val chairdips = ExerciseModel(3,
                "Neck Rolls",R.drawable.neckrolls,false,false)
            exerciseList.add(chairdips)

            val crunches = ExerciseModel(4,
                "Crunches",R.drawable.crunches2,false,false)
            exerciseList.add(crunches)

            val deadbug = ExerciseModel(5,
                "Plank Shoulder Taps",R.drawable.plankshouldertaps,false,false)
            exerciseList.add(deadbug)

            val glutebridges = ExerciseModel(6,
                "Back Stretch",R.drawable.backstretch,false,false)
            exerciseList.add(glutebridges)

            val kneelingpushups = ExerciseModel(7,
                "Mountain Climber",R.drawable.mountainclimber,false,false)
            exerciseList.add(kneelingpushups)

            val kneelingsideplanks = ExerciseModel(8,
                "Wrist Curls",R.drawable.wristcurls,false,false)
            exerciseList.add(kneelingsideplanks)

            val laterallegraises = ExerciseModel(9,
                "Lateral Leg Swings",R.drawable.laterallegswings,false,false)
            exerciseList.add(laterallegraises)

            val lowerbackshit = ExerciseModel(10,
                "Pilates",R.drawable.pilateshundred,false,false)
            exerciseList.add(lowerbackshit)

            val lunges = ExerciseModel(11,
                "Lunge Twist",R.drawable.lungetwist,false,false)
            exerciseList.add(lunges)

            val situps = ExerciseModel(12,
                "Standing Side Bend",R.drawable.standingsidebend,false,false)
            exerciseList.add(situps)

            return exerciseList
        }
    }
}
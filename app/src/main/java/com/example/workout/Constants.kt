package com.example.workout

class Constants {

    companion object {
        fun defaultExerciseList():ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val basicsquats = ExerciseModel(1,
            "Squats",R.drawable.basicsquats,false,false)
            exerciseList.add(basicsquats)

            val bicepcurls = ExerciseModel(2,
                "Bicep Curls",R.drawable.bicepcurls,false,false)
            exerciseList.add(bicepcurls)

            val chairdips = ExerciseModel(3,
                "Chair Dips",R.drawable.chairdips,false,false)
            exerciseList.add(chairdips)

            val crunches = ExerciseModel(4,
                "Crunches",R.drawable.crunches,false,false)
            exerciseList.add(crunches)

            val deadbug = ExerciseModel(5,
                "DeadBug",R.drawable.deadbug,false,false)
            exerciseList.add(deadbug)

            val glutebridges = ExerciseModel(6,
                "Glute Bridges",R.drawable.glutebridges,false,false)
            exerciseList.add(glutebridges)

            val kneelingpushups = ExerciseModel(7,
                "Kneeling PushUps",R.drawable.kneelingpushups,false,false)
            exerciseList.add(kneelingpushups)

            val kneelingsideplanks = ExerciseModel(8,
                "Kneeling Side Planks",R.drawable.kneelingsideplanks,false,false)
            exerciseList.add(kneelingsideplanks)

            val laterallegraises = ExerciseModel(9,
                "Lateral Leg Raises",R.drawable.laterallegraises,false,false)
            exerciseList.add(laterallegraises)

            val lowerbackshit = ExerciseModel(10,
                "Lower Back",R.drawable.lowerbackshit,false,false)
            exerciseList.add(lowerbackshit)

            val lunges = ExerciseModel(11,
                "Lunges",R.drawable.lunges,false,false)
            exerciseList.add(lunges)

            val situps = ExerciseModel(12,
                "SitUps",R.drawable.situps,false,false)
            exerciseList.add(situps)

            return exerciseList
        }
    }
}
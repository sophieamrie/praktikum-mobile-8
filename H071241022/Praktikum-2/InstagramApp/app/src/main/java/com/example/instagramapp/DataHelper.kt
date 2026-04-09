package com.example.instagramapp

import com.example.instagramapp.model.Post
import com.example.instagramapp.model.Story
import com.example.instagramapp.model.User

object DataHelper {

    val currentUser = User(
        id = 2,
        username = "sophieamri",
        fullName = "sophie amrie",
        bio = "@filtersen",
        profileImage = R.drawable.sophie,
        postCount = 5,
        followerCount = 5400,
        followingCount = 210
    )

    val userList = listOf(
        User(3, "zhaoyufan", "'James' Zhao Yufan", "'05 Liner", R.drawable.zhao, 5, 3200, 180),
        User(4, "mjedwards", "Martin Edwards Park", "MJ ever since 23", R.drawable.martin, 5, 2100, 90),
        User(5, "kjhoon", "Kim Juhoon", "its alright", R.drawable.kjh, 5, 1500, 300),
        User(6, "seansean", "Sean Eom", "17", R.drawable.eom, 5, 8900, 150),
        User(7, "keonho14", "Ahn Keonho", "swim", R.drawable.kk, 5, 12000, 400),
        User(8, "hike.reza", "Reza Hiker", "🏔️ Hiker", R.drawable.hiker, 1, 6700, 220),
        User(9, "coffee.tama", "Tama Coffee", "☕ Coffee addict", R.drawable.coffee, 1, 980, 75),
        User(10, "fitlife.nana", "Nana Fitness", "💪 Gym rat", R.drawable.fot, 1, 4300, 190),
        User(11, "sky.watcher", "Sky Watcher", "🌌 Astrophoto", R.drawable.sun, 1, 7800, 130)
    )

    val homeFeedList by lazy {
        mutableListOf(
            userPostsMap[2]?.get(1)!!,
            userPostsMap[3]?.get(0)!!,
            userPostsMap[4]?.get(0)!!,
            userPostsMap[5]?.get(0)!!,
            userPostsMap[6]?.get(0)!!,
            userPostsMap[7]?.get(0)!!,
            userPostsMap[8]?.get(0)!!,
            userPostsMap[9]?.get(0)!!,
            userPostsMap[10]?.get(0)!!,
            userPostsMap[11]?.get(0)!!
        )
    }

    val userPostsMap = mutableMapOf(
        2 to mutableListOf(
            Post(11, 2, "sophieamri", R.drawable.sophie, R.drawable.photo1, "Boyfriend", 245, "2 hours ago"),
            Post(12, 2, "sophieamri", R.drawable.sophie, R.drawable.photo2, "beaches", 312, "1 day ago"),
            Post(13, 2, "sophieamri", R.drawable.sophie, R.drawable.photo3, "ow", 198, "3 days ago"),
            Post(14, 2, "sophieamri", R.drawable.sophie, R.drawable.photo4, "boyfriend 2", 421, "1 week ago"),
            Post(15, 2, "sophieamri", R.drawable.sophie, R.drawable.photo5, "owwwwwwww", 287, "2 weeks ago")
        ),
        3 to mutableListOf(
            Post(21, 3, "zhaoyufan", R.drawable.zhao, R.drawable.zhao1, "Torriden", 531, "4 hours ago"),
            Post(22, 3, "zhaoyufan", R.drawable.zhao, R.drawable.zhao2, "airport fit", 672, "2 days ago"),
            Post(23, 3, "zhaoyufan", R.drawable.zhao, R.drawable.zhao3, "he said it was yoga", 445, "4 days ago"),
            Post(24, 3, "zhaoyufan", R.drawable.zhao, R.drawable.zhao4, "kt lets go", 389, "1 week ago"),
            Post(25, 3, "zhaoyufan", R.drawable.zhao, R.drawable.zhao5, "i-D magazine", 512, "2 weeks ago")
        ),
        4 to mutableListOf(
            Post(31, 4, "mjedwards", R.drawable.martin, R.drawable.martin1, "pilates they say", 189, "5 hours ago"),
            Post(32, 4, "mjedwards", R.drawable.martin, R.drawable.martin2, "greenGreen", 234, "1 day ago"),
            Post(33, 4, "mjedwards", R.drawable.martin, R.drawable.martin3, "winks", 156, "3 days ago"),
            Post(34, 4, "mjedwards", R.drawable.martin, R.drawable.martin4, "homies", 298, "5 days ago"),
            Post(35, 4, "mjedwards", R.drawable.martin, R.drawable.martin5, "shii was cold", 341, "1 week ago")
        ),
        5 to mutableListOf(
            Post(41, 5, "kjhoon", R.drawable.kjh, R.drawable.kjh1, "maaf klo terlalu suit", 302, "6 hours ago"),
            Post(42, 5, "kjhoon", R.drawable.kjh, R.drawable.kjh2, "pilates", 187, "2 days ago"),
            Post(43, 5, "kjhoon", R.drawable.kjh, R.drawable.kjh3, "late night sush", 245, "4 days ago"),
            Post(44, 5, "kjhoon", R.drawable.kjh, R.drawable.kjh4, "you think im performative?", 198, "1 week ago"),
            Post(45, 5, "kjhoon", R.drawable.kjh, R.drawable.kjh5, "jjami", 312, "2 weeks ago")
        ),
        6 to mutableListOf(
            Post(51, 6, "seansean", R.drawable.eom, R.drawable.eom1, "bridge ver", 421, "8 hours ago"),
            Post(52, 6, "seansean", R.drawable.eom, R.drawable.eom2, "me and gang", 389, "1 day ago"),
            Post(53, 6, "seansean", R.drawable.eom, R.drawable.eom3, "woop", 267, "3 days ago"),
            Post(54, 6, "seansean", R.drawable.eom, R.drawable.eom5, "slickback sumn", 445, "5 days ago"),
            Post(55, 6, "seansean", R.drawable.eom, R.drawable.eom4, "cheeze", 512, "1 week ago")
        ),
        7 to mutableListOf(
            Post(61, 7, "keonho14", R.drawable.kk, R.drawable.kk1, "kt waltsz lets goooooooo", 678, "10 hours ago"),
            Post(62, 7, "keonho14", R.drawable.kk, R.drawable.kk2, "yessir", 534, "2 days ago"),
            Post(63, 7, "keonho14", R.drawable.kk, R.drawable.kk3, "aha", 612, "4 days ago"),
            Post(64, 7, "keonho14", R.drawable.kk, R.drawable.kk4, "eom", 489, "1 week ago"),
            Post(65, 7, "keonho14", R.drawable.kk, R.drawable.kk5, "cool like that", 723, "2 weeks ago")
        ),
        8 to mutableListOf(
            Post(71, 8, "hike.reza", R.drawable.hiker, R.drawable.hiker1, "#hiking", 892, "1 day ago"),
        ),
        9 to mutableListOf(
            Post(81, 9, "coffee.tama", R.drawable.coffee, R.drawable.coffee1, "Morning latte art ☕ #coffee", 156, "1 day ago"),
        ),
        10 to mutableListOf(
            Post(91, 10, "fitlife.nana", R.drawable.fot, R.drawable.fot1, "Leg day done 💪 #gym", 234, "2 days ago"),
        ),
        11 to mutableListOf(
            Post(101, 11, "sky.watcher", R.drawable.sun, R.drawable.sun1, "Milky way 🌌 #astrophotography", 1024, "2 days ago"),
        )
    )

    val highlightMap = mapOf(
        2 to mutableListOf(
            Story(1, "", R.drawable.story1, 2),
            Story(2, "", R.drawable.story2, 2),
            Story(3, "", R.drawable.story3, 2),
            Story(4, "", R.drawable.story4, 2),
            Story(5, "", R.drawable.story5, 2),
            Story(6, "", R.drawable.story6, 2),
            Story(7, "", R.drawable.story7, 2)
        ),
        3 to mutableListOf(
            Story(11, "shoots", R.drawable.zhao6, 3),
        ),
        4 to mutableListOf(
            Story(21, "i", R.drawable.martin6, 4),
        ),
        5 to mutableListOf(
            Story(31, "airport", R.drawable.kjh6, 5),
        ),
        6 to mutableListOf(
            Story(41, "photoshoots", R.drawable.eom6, 6),
        ),
        7 to mutableListOf(
            Story(51, "me", R.drawable.kk6, 7),
        ),
        8 to mutableListOf(
            Story(61, "Semeru", R.drawable.hiker2, 8),
        ),
        9 to mutableListOf(
            Story(71, "Latte", R.drawable.coffee2, 9),
        ),
        10 to mutableListOf(
            Story(81, "Gym", R.drawable.fot2, 10),
        ),
        11 to mutableListOf(
            Story(91, "Milky Way", R.drawable.sun2, 11),
        )
    )
}
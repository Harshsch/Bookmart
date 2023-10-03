package com.example.bookmart

import com.example.bookmart.data.AddToCart.CartItem

data class BuyNowData(
    val name: String,
    val streetAddress: String,
    val city: String,
    val productname: String,
    val priceperunit: Int,
    val quantity: Int,
    val totalprice: String

)
data class CartBuyNowData(
    val name: String,
    val streetAddress: String,
    val city: String,
    val cartItems: List<CartItem>,
    //val quantity: Int,


)


data class ListItem(
    val id: Int,
    val imageResId: Int,
    val name: String,
    val price: Int,
    val department: String,
    val year: Int,
    val semester: Int,
    val description: String
)

val itemList = listOf(
    ListItem(1, R.drawable.img_1, "COMPUTER NETWORKS", 375, "Computer", 2023, 1, "A comprehensive guide to computer networks with real-world examples."),
    ListItem(2, R.drawable.img_2, "CRYPTOGRAPHY AND CYBER SECURITY", 245, "Computer", 2023, 2, "Learn the art of securing data and communications."),
    ListItem(3, R.drawable.img_3, "ANDROID DEVELOPMENT", 195, "Computer", 2022, 2, "Build mobile apps for Android with hands-on projects."),
    ListItem(4, R.drawable.img_4, "BLOCKCHAIN TECHNOLOGY", 260, "Computer", 2023, 1, "Explore the revolutionary world of blockchain."),
    ListItem(5, R.drawable.img_5, "CLOUD COMPUTING", 245, "Computer", 2022, 1, "Get started with cloud computing and its applications."),
    ListItem(6, R.drawable.img_6, "COMPUTER AIDED ENGINEERING", 415, "Engineering", 2022, 2, "Learn how to use computers for engineering design and analysis."),
    ListItem(7, R.drawable.img_7, "CELLULAR NETWORKS", 275, "Engineering", 2023, 1, "Explore the world of cellular communication and networking."),
    ListItem(8, R.drawable.img_8, "COMPUTER GRAPHICS", 415, "Computer", 2022, 2, "Master the art of computer-generated graphics and visualizations."),
    ListItem(9, R.drawable.img_9, "DATA SCIENCE", 105, "Computer", 2023, 2, "Dive into the world of data analysis and machine learning."),
    ListItem(10, R.drawable.img_10, "DEEP LEARNING", 295, "Computer", 2022, 1, "Discover advanced techniques in deep neural networks."),
    ListItem(11, R.drawable.fe_img_1, "ENGINEERING MATHEMATICS - I FOR SPPU 19 COURSE (FE - I - COMMON - 107001)", 555, "First Year", 2023, 1, "A fundamental mathematics course for engineering students."),
    ListItem(12, R.drawable.fe_img_2, "ENGINEERING MATHEMATICS - II FOR SPPU 19 COURSE (FE - II - COMMON - 107008)", 495, "First Year", 2023, 2, "Continuation of the first-semester mathematics course."),
    ListItem(13, R.drawable.fe_img_3, "ENGINEERING PHYSICS FOR SPPU 19 COURSE (FE - I/II - COMMON - 107002)", 325, "First Year", 2022, 1, "Learn the principles of physics relevant to engineering."),
    ListItem(14, R.drawable.fe_img_4, "ENGINEERING CHEMISTRY FOR SPPU 19 COURSE (FE - I/II - COMMON - 107009)", 295, "First Year", 2023, 1, "Chemistry concepts tailored for engineering students."),
    ListItem(15, R.drawable.fe_img_5, "ENGINEERING GRAPHICS FOR SPPU 19 COURSE (FE - II - COMMON - 102012)", 395, "First Year", 2022, 1, "Introduction to engineering drawing and graphics."),
    ListItem(16, R.drawable.fe_img_6, "ENGINEERING MECHANICS FOR SPPU 19 COURSE (FE - I/II - COMMON - 101011)", 595, "First Year", 2022, 2, "Study the mechanics of materials and systems in engineering."),
    ListItem(17, R.drawable.fe_img_7, "BASIC ELECTRICAL ENGINEERING FOR SPPU 19 COURSE (FE - I/II - COMMON - 103004)", 425, "First Year", 2023, 1, "Learn the fundamentals of electrical engineering."),
    ListItem(18, R.drawable.fe_img_8, "BASIC ELECTRONICS ENGINEERING FOR SPPU 19 COURSE (FE - I/II - COMMON - 104010)", 395, "First Year", 2022, 2, "Introduction to electronic circuits and devices."),
    ListItem(19, R.drawable.fe_img_9, "PROGRAMMING & PROBLEM SOLVING FOR SPPU 19 COURSE (FE - I/II - COMMON - 110005)", 295, "First Year", 2023, 2, "Learn programming and problem-solving techniques."),
    ListItem(20, R.drawable.fe_img_10, "SYSTEMS IN MECHANICAL ENGINEERING FOR SPPU 19 COURSE (FE - I - COMMON - 102003)", 425, "First Year", 2022, 1, "Introduction to mechanical engineering systems."),
    ListItem(21, R.drawable.cse_img_1, "DISCRETE MATHEMATICS FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210241)", 375, "Computer Science", 2, 1, "Study discrete structures and their applications in computer science."),
    ListItem(22, R.drawable.cse_img_2, "FUNDAMENTALS OF DATA STRUCTURES FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210242)", 320, "Computer Science", 2, 2, "Learn essential data structures and algorithms."),
    ListItem(23, R.drawable.cse_img_3, "OBJECT ORIENTED PROGRAMMING FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210243)", 290, "Computer Science", 2, 1, "Master object-oriented programming concepts."),
    ListItem(24, R.drawable.cse_img_4, "COMPUTER GRAPHICS FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210244)", 310, "Computer Science", 2, 1, "Explore computer graphics and rendering techniques."),
    ListItem(25, R.drawable.cse_img_5, "DIGITAL ELECTRONICS & LOGIC DESIGN FOR SPPU 19 COURSE (SE - III - COMP.- 210245)", 355, "Computer Science", 2, 1, "Learn digital electronics and logic circuit design."),
    ListItem(26, R.drawable.cse_img_6, "DATA STRUCTURES & ALGORITHMS FOR SPPU 19 COURSE (SE - IV - COMP - 210252) & (SE - SEM IV -AI&DS- 210252)", 495, "Computer Science", 2, 2, "Advanced data structures and algorithms for efficient computing."),
    ListItem(27, R.drawable.cse_img_7, "ENGINEERING MATHEMATICS- III FOR SPPU 19 COURSE (SE - IV - COMP./IT - 207003)", 495, "Computer Science", 2, 1, "Mathematical concepts for computer science applications."),
    ListItem(28, R.drawable.cse_img_8, "MICROPROCESSOR FOR SPPU 19 COURSE (SE - IV - COMP. - 210254)", 295, "Computer Science", 2, 2, "Study microprocessor architecture and assembly language programming."),
    ListItem(29, R.drawable.cse_img_9, "PRINCIPLES OF PROGRAMMING LANGUAGES FOR SPPU 19 COURSE (SE - IV - COMP. - 210255)", 335, "Computer Science", 2, 2, "Explore programming languages and paradigms."),
    ListItem(30, R.drawable.cse_img_10, "THEORY OF COMPUTATION FOR SPPU 19 COURSE (TE - SEM V - COMP.- 310242)", 450, "Computer Science", 3, 1, "Study formal languages, automata, and computational theory."),
    ListItem(31, R.drawable.cse_img_11, "THEORY OF COMPUTATION FOR SPPU 19 COURSE (TE - SEM V - COMP.- 310242)", 495, "Computer Science", 3, 1, "A continuation of the theory of computation."),
    ListItem(32, R.drawable.cse_img_12, "SYSTEM PROGRAMMING AND OPERATING SYSTEMS FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310243)", 390, "Computer Science", 3, 2, "Learn about operating systems and system programming."),
    ListItem(34, R.drawable.cse_img_14, "DISTRIBUTED SYSTEM FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310245(C)) ELECTIVE I", 260, "Computer Science", 3, 1, "Explore distributed computing and networked systems."),
    ListItem(35, R.drawable.cse_img_15, "DATABASE MANAGEMENT SYSTEMS FOR SPPU 19 COURSE (TE - SEM V - COMP/AI&DS - 310241)", 395, "Computer Science", 3, 1, "Learn about database systems and management."),
    ListItem(36, R.drawable.cse_img_16, "SOFTWARE PROJECT MANAGEMENT FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310245(D)) ELECTIVE I", 315, "Computer Science", 3, 2, "Master project management in software development."),
    ListItem(37, R.drawable.cse_img_17, "INTERNET OF THINGS AND EMBEDDED SYSTEMS FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310245(A)) - ELECTIVE I", 350, "Computer Science", 3, 1, "Explore IoT and embedded systems."),
    ListItem(38, R.drawable.cse_img_18, "HUMAN COMPUTER INTERFACE FOR SPPU 19 COURSE (TE - SEM V - COMP/AI&DS - 310245(B)) - ELECTIVE I", 230, "Computer Science", 3, 2, "Study user interface design and human-computer interaction."),
    ListItem(39, R.drawable.cse_img_19, "ARTIFICIAL INTELLIGENCE FOR SPPU 19 COURSE (TE - SEM VI - COMP. & TE - SEM V - AI&DS - 310253)", 490, "Computer Science", 3, 2, "Dive into the field of artificial intelligence."),
    ListItem(40, R.drawable.cse_img_20, "WEB TECHNOLOGY FOR SPPU 19 COURSE (TE - SEM VI - COMP. & TE - SEM - V - AI&DS - 310252)", 595, "Computer Science", 3, 1, "Learn web development technologies and practices."),
    ListItem(41, R.drawable.cse_img_21, "INFORMATION SECURITY FOR SPPU 19 COURSE (TE - SEM VI - COMP - 310254(A)) - ELECTIVE -I", 380, "Computer Science", 3, 1, "Master the art of information security."),
    ListItem(42, R.drawable.img_5, "CLOUD COMPUTING FOR SPPU 19 COURSE (TE - SEM VI - COMP/AI&DS - 310254(C)) - ELECTIVE II", 245, "Computer Science", 3, 2, "Explore cloud computing technologies and services."),
    ListItem(43, R.drawable.cse_img_23, "AUGMENTED REALITY/VIRTUAL REALITY FOR BE ANNA UNIVERSITY R21CBCS (VERTICAL V/VI - CSE / IT / AI&DS, VERTICAL III - CS&BS- CCS333)", 385, "Computer Science", 3, 1, "Discover the world of augmented and virtual reality."),
    ListItem(44, R.drawable.cse_img_24, "SOFTWARE MODELLING & ARCHITECTURES FOR SPPU 19 COURSE (TE - SEM VI - COMP/AI&DS - 310254(D)) - ELECTIVE - II", 365, "Computer Science", 3, 1, "Learn about software modeling and system architectures."),
    ListItem(46, R.drawable.cse_img_26, "MACHINE LEARNING FOR SPPU 19 COURSE (BE - SEM VII -COMP) - 410242", 275, "Computer Science", 4, 1, "Explore the field of machine learning."),
    ListItem(47, R.drawable.cse_img_27, "DESIGN AND ANALYSIS OF ALGORITHMS FOR SPPU 19 COURSE (BE - SEM VII -COMP)- 410241", 350, "Computer Science", 4, 2, "Master the design and analysis of algorithms."),
    ListItem(48, R.drawable.cse_img_28, "COMPILERS FOR SPPU 19 COURSE (BE - SEM VII -COMP) - ELECTIVE IV - 410252(E)", 335, "Computer Science", 4, 1, "Study compiler construction and theory."),
    ListItem(49, R.drawable.cse_img_29, "OBJECT ORIENTED MODELING AND DESIGN FOR SPPU 19 COURSE (BE - SEM VII -COMP) - ELECTIVE III - 410244(D)", 350, "Computer Science", 4, 1, "Learn object-oriented modeling and design principles."),
    ListItem(50, R.drawable.cse_img_30, "DEEP LEARNING FOR SPPU 19 COURSE (BE - SEM VIII - COMP. - 410251)", 225, "Computer Science", 4, 2, "Deep dive into advanced deep learning techniques."),
    ListItem(51, R.drawable.cse_img_31, "HIGH PERFORMANCE COMPUTING FOR SPPU 19 COURSE (BE - SEM VIII - COMP. - 410250)", 275, "Computer Science", 4, 2, "Explore high-performance computing technologies."),
    ListItem(52, R.drawable.cse_img_32, "SOFT COMPUTING FOR SPPU 19 COURSE (BE - SEM VIII - COMP - 410253(B)) - ELECTIVE VI", 230, "Computer Science", 4, 1, "Study soft computing and its applications."),
    ListItem(53, R.drawable.cse_img_33, "BUSINESS INTELLIGENCE FOR SPPU 19 COURSE (BE - SEM VIII - COMP - 410253(C)) - ELECTIVE VI", 295, "Computer Science", 4, 1, "Master business intelligence concepts and tools."),
    ListItem(54, R.drawable.cse_img_34, "NATURAL LANGUAGE PROCESSING FOR SPPU 19 COURSE (BE - SEM VIII - COMP. - 410252(A)) - ELECTIVE V", 240, "Computer Science", 4, 2, "Explore natural language processing and text analysis.")
//    ListItem(55, R.drawable.cse_img_35, "SOFTWARE TESTING", "Rs. 315.00", "Computer Science", 2022, 1),
//    ListItem(56, R.drawable.cse_img_36, "ARTIFICIAL INTELLIGENCE", "Rs. 390.00", "Computer Science", 2023, 1),
//    ListItem(57, R.drawable.cse_img_37, "DATA SCIENCE", "Rs. 350.00", "Computer Science", 2023, 2),
//    ListItem(58, R.drawable.cse_img_38, "WEB DEVELOPMENT", "Rs. 320.00", "Computer Science", 2022, 1),
//    ListItem(59, R.drawable.cse_img_39, "MOBILE APPLICATION DEVELOPMENT", "Rs. 285.00", "Computer Science", 2023, 1),
//    ListItem(60, R.drawable.cse_img_40, "NETWORK SECURITY", "Rs. 345.00", "Computer Science", 2022, 2)

)


// Sorting function to sort by department, year, and semester
//fun sortBooks() {
//    itemList.sortBy { it.department }
//    itemList.sortBy { it.year }
//    itemList.sortBy { it.semester }
//}
//
//// Call the sorting function
//sortBooks()
//
//// Display the sorted books on the home screen
//for (book in itemList) {
//    println("${book.name} - ${book.department} - Year ${book.year}, Semester ${book.semester} - Price: ${book.price}")
//}



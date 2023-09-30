package com.example.bookmart
data class ListItem(
    val id: Int,
    val imageResId: Int,
    val name: String,
    val price: String, // Changed from "Rs. 375.00" to just price
    val department: String, // Add department property
    val year: Int,          // Add year property
    val semester: Int       // Add semester property
)

val itemList = listOf(
    ListItem(1, R.drawable.img_1, "COMPUTER NETWORKS", "Rs. 375.00", "Computer ", 2023, 1),
    ListItem(2, R.drawable.img_2, "CRYPTOGRAPHY AND CYBER SECURITY", "Rs. 245.00", "Computer ", 2023, 2),
    ListItem(3, R.drawable.img_3, "ANDROID DEVELOPMENT", "Rs. 195.00", "Computer ", 2022, 2),
    ListItem(4, R.drawable.img_4, "BLOCKCHAIN TECHNOLOGY", "Rs. 260.00", "Computer ", 2023, 1),
    ListItem(5, R.drawable.img_5, "CLOUD COMPUTING", "Rs. 245.00", "Computer ", 2022, 1),
    ListItem(6, R.drawable.img_6, "COMPUTER AIDED ENGINEERING", "Rs. 415.00", "Engineering", 2022, 2),
    ListItem(7, R.drawable.img_7, "CELLULAR NETWORKS", "Rs. 275.00", "Engineering", 2023, 1),
    ListItem(8, R.drawable.img_8, "COMPUTER GRAPHICS", "Rs. 415.00", "Computer ", 2022, 2),
    ListItem(9, R.drawable.img_9, "DATA SCIENCE", "Rs. 105.00", "Computer ", 2023, 2),
    ListItem(10, R.drawable.img_10, "DEEP LEARNING", "Rs. 295.00", "Computer ", 2022, 1),
    // Add more books with department, year, and semester information
    ListItem(11, R.drawable.fe_img_1, "ENGINEERING MATHEMATICS - I FOR SPPU 19 COURSE (FE - I - COMMON - 107001)", "Rs. 555.00", "First Year", 2023, 1),
    ListItem(12, R.drawable.fe_img_2, "ENGINEERING MATHEMATICS - II FOR SPPU 19 COURSE (FE - II - COMMON - 107008)", "Rs. 495.00", "First Year", 2023, 2),
    ListItem(13, R.drawable.fe_img_3, "ENGINEERING PHYSICS FOR SPPU 19 COURSE (FE - I/II - COMMON - 107002)", "Rs. 325.00", "First Year", 2022, 1),
    ListItem(14, R.drawable.fe_img_4, "ENGINEERING CHEMISTRY FOR SPPU 19 COURSE (FE - I/II - COMMON - 107009)", "Rs. 295.00", "First Year", 2023, 1),
    ListItem(15, R.drawable.fe_img_5, "ENGINEERING GRAPHICS FOR SPPU 19 COURSE (FE - II - COMMON - 102012)", "Rs. 395.00", "First Year", 2022, 1),
    ListItem(16, R.drawable.fe_img_6, "ENGINEERING MECHANICS FOR SPPU 19 COURSE (FE - I/II - COMMON - 101011)", "Rs. 595.00", "First Year", 2022, 2),
    ListItem(17, R.drawable.fe_img_7, "BASIC ELECTRICAL ENGINEERING FOR SPPU 19 COURSE (FE - I/II - COMMON - 103004)", "Rs. 425.00", "First Year", 2023, 1),
    ListItem(18, R.drawable.fe_img_8, "BASIC ELECTRONICS ENGINEERING FOR SPPU 19 COURSE (FE - I/II - COMMON - 104010)", "Rs. 395.00", "First Year", 2022, 2),
    ListItem(19, R.drawable.fe_img_9, "PROGRAMMING & PROBLEM SOLVING FOR SPPU 19 COURSE (FE - I/II - COMMON - 110005)", "Rs. 295.00", "First Year", 2023, 2),
    ListItem(20, R.drawable.fe_img_10, "SYSTEMS IN MECHANICAL ENGINEERING FOR SPPU 19 COURSE (FE - I - COMMON - 102003)", "Rs. 425.00", "First Year", 2022, 1),

    ListItem(21, R.drawable.cse_img_1, "DISCRETE MATHEMATICS FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210241)", "Rs. 375.00", "Computer Science", 2, 1),
    ListItem(22, R.drawable.cse_img_2, "FUNDAMENTALS OF DATA STRUCTURES FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210242)", "Rs. 320.00", "Computer Science", 2, 2),
    ListItem(23, R.drawable.cse_img_3, "OBJECT ORIENTED PROGRAMMING FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210243)", "Rs. 290.00", "Computer Science", 2, 1),
    ListItem(24, R.drawable.cse_img_4, "COMPUTER GRAPHICS FOR SPPU 19 COURSE (SE - III - COMP/AI&DS - 210244)", "Rs. 310.00", "Computer Science", 2, 1),
    ListItem(25, R.drawable.cse_img_5, "DIGITAL ELECTRONICS & LOGIC DESIGN FOR SPPU 19 COURSE (SE - III - COMP.- 210245)", "Rs. 355.00", "Computer Science", 2, 1),
    ListItem(26, R.drawable.cse_img_6, "DATA STRUCTURES & ALGORITHMS FOR SPPU 19 COURSE (SE - IV - COMP - 210252) & (SE - SEM IV -AI&DS- 210252)", "Rs. 495.00", "Computer Science", 2, 2),
    ListItem(27, R.drawable.cse_img_7, "ENGINEERING MATHEMATICS- III FOR SPPU 19 COURSE (SE - IV - COMP./IT - 207003)", "Rs. 495.00", "Computer Science", 2, 1),
    ListItem(28, R.drawable.cse_img_8, "MICROPROCESSOR FOR SPPU 19 COURSE (SE - IV - COMP. - 210254)", "Rs. 295.00", "Computer Science", 2, 2),
    ListItem(29, R.drawable.cse_img_9, "PRINCIPLES OF PROGRAMMING LANGUAGES FOR SPPU 19 COURSE (SE - IV - COMP. - 210255)", "Rs. 335.00", "Computer Science", 2, 2),
    ListItem(30, R.drawable.cse_img_10, "THEORY OF COMPUTATION FOR SPPU 19 COURSE (TE - SEM V - COMP.- 310242)", "Rs. 450.00", "Computer Science", 3, 1),
    ListItem(31, R.drawable.cse_img_11, "THEORY OF COMPUTATION FOR SPPU 19 COURSE (TE - SEM V - COMP.- 310242)", "Rs. 495.00", "Computer Science", 3, 1),
    ListItem(32, R.drawable.cse_img_12, "SYSTEM PROGRAMMING AND OPERATING SYSTEMS FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310243)", "Rs. 390.00", "Computer Science", 3, 2),
    //ListItem(33, R.drawable.cse_img_13, "DATABASE MANAGEMENT SYSTEMS", "Rs. 290.00", "Computer Science", 2022, 1),
    ListItem(34, R.drawable.cse_img_14, "DISTRIBUTED SYSTEM FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310245(C)) ELECTIVE I", "Rs. 260.00", "Computer Science", 3, 1),
    ListItem(35, R.drawable.cse_img_15, "DATABASE MANAGEMENT SYSTEMS FOR SPPU 19 COURSE (TE - SEM V - COMP/AI&DS - 310241)", "Rs. 395.00", "Computer Science", 3, 1),
    ListItem(36, R.drawable.cse_img_16, "SOFTWARE PROJECT MANAGEMENT FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310245(D)) ELECTIVE I", "Rs. 315.00", "Computer Science", 3, 2),
    ListItem(37, R.drawable.cse_img_17, "INTERNET OF THINGS AND EMBEDDED SYSTEMS FOR SPPU 19 COURSE (TE - SEM V - COMP. - 310245(A)) - ELECTIVE I", "Rs. 350.00", "Computer Science", 3, 1),
    ListItem(38, R.drawable.cse_img_18, "HUMAN COMPUTER INTERFACE FOR SPPU 19 COURSE (TE - SEM V - COMP/AI&DS - 310245(B)) - ELECTIVE I", "Rs. 230.00", "Computer Science", 3, 2),
    ListItem(39, R.drawable.cse_img_19, "ARTIFICIAL INTELLIGENACE FOR SPPU 19 COURSE (TE - SEM VI - COMP. & TE - SEM V - AI&DS - 310253)", "Rs. 490.00", "Computer Science", 3, 2),
    ListItem(40, R.drawable.cse_img_20, "WEB TECHNOLOGY FOR SPPU 19 COURSE (TE - SEM VI - COMP. & TE - SEM - V - AI&DS - 310252)", "Rs. 595.00", "Computer Science", 3, 1),
    ListItem(41, R.drawable.cse_img_21, "INFORMATION SECURITY FOR SPPU 19 COURSE (TE - SEM VI - COMP - 310254(A)) - ELECTIVE -I", "Rs. 380.00", "Computer Science", 3, 1),
    ListItem(42, R.drawable.img_5, "CLOUD COMPUTING FOR SPPU 19 COURSE (TE - SEM VI - COMP/AI&DS - 310254(C)) - ELECTIVE II", "Rs. 245.00", "Computer Science", 3, 2),
    ListItem(43, R.drawable.cse_img_23, "AUGMENTED REALITY/VIRTUAL REALITY FOR BE ANNA UNIVERSITY R21CBCS (VERTICAL V/VI - CSE / IT / AI&DS, VERTICAL III - CS&BS- CCS333)", "Rs. 385.00", "Computer Science", 3, 1),
    ListItem(44, R.drawable.cse_img_24, "SOFTWARE MODELLING & ARCHITECTURES FOR SPPU 19 COURSE (TE - SEM VI - COMP/AI&DS - 310254(D)) - ELECTIVE - II", "Rs. 365.00", "Computer Science", 3, 1),
    ListItem(4, R.drawable.img_4, "BLOCKCHAIN TECHNOLOGY FOR SPPU 19 COURSE (BE - SEM VII -COMP) - 410243", "Rs. 260.00", "Computer Science", 4, 1),
    ListItem(46, R.drawable.cse_img_26, "MACHINE LEARNING FOR SPPU 19 COURSE (BE - SEM VII -COMP) - 410242", "Rs. 275.00", "Computer Science", 4, 1),
    ListItem(47, R.drawable.cse_img_27, "DESIGN AND ANALYSIS OF ALGORITHMS FOR SPPU 19 COURSE (BE - SEM VII -COMP)- 410241", "Rs. 350.00", "Computer Science", 4, 2),
    ListItem(48, R.drawable.cse_img_28, "COMPILERS FOR SPPU 19 COURSE (BE - SEM VII -COMP) - ELECTIVE IV - 410252(E)", "Rs. 335.00", "Computer Science", 4, 1),
    ListItem(49, R.drawable.cse_img_29, "OBJECT ORIENTED MODELING AND DESIGN FOR SPPU 19 COURSE (BE - SEM VII -COMP) - ELECTIVE III - 410244(D)", "Rs. 350.00", "Computer Science", 4, 1),
    ListItem(50, R.drawable.cse_img_30, "DEEP LEARNING FOR SPPU 19 COURSE (BE - SEM VIII - COMP. - 410251)", "Rs. 225.00", "Computer Science", 4, 2),
    ListItem(51, R.drawable.cse_img_31, "HIGH PERFORMANCE COMPUTING FOR SPPU 19 COURSE (BE - SEM VIII - COMP. - 410250)", "Rs. 275.00", "Computer Science", 4, 2),
    ListItem(52, R.drawable.cse_img_32, "SOFT COMPUTING FOR SPPU 19 COURSE (BE - SEM VIII - COMP - 410253(B)) - ELECTIVE VI", "Rs. 230.00", "Computer Science", 4, 1),
    ListItem(53, R.drawable.cse_img_33, "BUSINESS INTELLIGENCE FOR SPPU 19 COURSE (BE - SEM VIII - COMP - 410253(C)) - ELECTIVE VI", "Rs. 295.00", "Computer Science", 4, 1),
    ListItem(54, R.drawable.cse_img_34, "NATURAL LANGUAGE PROCESSING FOR SPPU 19 COURSE (BE - SEM VIII - COMP. - 410252(A)) - ELECTIVE V", "Rs. 240.00", "Computer Science", 4, 2),
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



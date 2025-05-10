package com.site7x24learn.internshipfrontend.presentation.navigation

object Routes {
    const val LOGIN = "login"
    const val SIGN_UP = "signup"
    const val ADD_INTERNSHIP = "add_internship"
    const val EDIT_INTERNSHIP="edit_internship/{internshipId}"
    const val ADMIN_DASHBOARD = "admin_dashboard"
    const val STUDENT_DASHBOARD = "student_dashboard"
    const val APPLY_INTERNSHIP = "apply_internship/{internshipId}"
    const val INTERNSHIP_LIST="internship_list"
    const val STUDENT_APPLICATIONS = "student_applications"
    const val PROFILE = "profile"
//    const val UPDATE_APPLICATION = "update_application/{applicationId}"

    fun editInternshipRoute(internshipId:Int)="edit_internship/$internshipId"
}
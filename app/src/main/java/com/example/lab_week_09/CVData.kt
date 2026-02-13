package com.example.lab_week_09

data class PersonalInfo(
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var address: String = "",
    var summary: String = ""
)

data class Education(
    var degree: String = "",
    var institution: String = "",
    var year: String = "",
    var gpa: String = ""
)

data class Experience(
    var position: String = "",
    var company: String = "",
    var duration: String = "",
    var description: String = ""
)

data class Skill(
    var name: String = "",
    var level: String = ""
)

data class CVData(
    var personalInfo: PersonalInfo = PersonalInfo(),
    var education: MutableList<Education> = mutableListOf(),
    var experience: MutableList<Experience> = mutableListOf(),
    var skills: MutableList<Skill> = mutableListOf()
)

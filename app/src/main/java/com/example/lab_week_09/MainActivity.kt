package com.example.lab_week_09

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab_week_09.ui.theme.LAB_WEEK_09Theme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_WEEK_09Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    val cvData = remember { mutableStateOf(CVData()) }
    
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            CVInputScreen(
                cvData = cvData.value,
                onCVDataChange = { cvData.value = it },
                onPreviewClick = { navController.navigate("preview") }
            )
        }
        composable("preview") {
            CVPreviewScreen(
                cvData = cvData.value,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun CVInputScreen(
    cvData: CVData,
    onCVDataChange: (CVData) -> Unit,
    onPreviewClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "CV Builder",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Personal Information Section
        Text(
            text = "Personal Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        OutlinedTextField(
            value = cvData.personalInfo.name,
            onValueChange = { 
                onCVDataChange(cvData.copy(personalInfo = cvData.personalInfo.copy(name = it)))
            },
            label = { Text("Full Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        
        OutlinedTextField(
            value = cvData.personalInfo.email,
            onValueChange = { 
                onCVDataChange(cvData.copy(personalInfo = cvData.personalInfo.copy(email = it)))
            },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        
        OutlinedTextField(
            value = cvData.personalInfo.phone,
            onValueChange = { 
                onCVDataChange(cvData.copy(personalInfo = cvData.personalInfo.copy(phone = it)))
            },
            label = { Text("Phone") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        
        OutlinedTextField(
            value = cvData.personalInfo.address,
            onValueChange = { 
                onCVDataChange(cvData.copy(personalInfo = cvData.personalInfo.copy(address = it)))
            },
            label = { Text("Address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
        
        OutlinedTextField(
            value = cvData.personalInfo.summary,
            onValueChange = { 
                onCVDataChange(cvData.copy(personalInfo = cvData.personalInfo.copy(summary = it)))
            },
            label = { Text("Professional Summary") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            minLines = 3
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Education Section
        Text(
            text = "Education",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        cvData.education.forEachIndexed { index, education ->
            EducationItem(
                education = education,
                onEducationChange = { newEdu ->
                    val newList = cvData.education.toMutableList()
                    newList[index] = newEdu
                    onCVDataChange(cvData.copy(education = newList))
                },
                onRemove = {
                    val newList = cvData.education.toMutableList()
                    newList.removeAt(index)
                    onCVDataChange(cvData.copy(education = newList))
                }
            )
        }
        
        Button(
            onClick = {
                val newList = cvData.education.toMutableList()
                newList.add(Education())
                onCVDataChange(cvData.copy(education = newList))
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("+ Add Education")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Experience Section
        Text(
            text = "Work Experience",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        cvData.experience.forEachIndexed { index, experience ->
            ExperienceItem(
                experience = experience,
                onExperienceChange = { newExp ->
                    val newList = cvData.experience.toMutableList()
                    newList[index] = newExp
                    onCVDataChange(cvData.copy(experience = newList))
                },
                onRemove = {
                    val newList = cvData.experience.toMutableList()
                    newList.removeAt(index)
                    onCVDataChange(cvData.copy(experience = newList))
                }
            )
        }
        
        Button(
            onClick = {
                val newList = cvData.experience.toMutableList()
                newList.add(Experience())
                onCVDataChange(cvData.copy(experience = newList))
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("+ Add Experience")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Skills Section
        Text(
            text = "Skills",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        
        cvData.skills.forEachIndexed { index, skill ->
            SkillItem(
                skill = skill,
                onSkillChange = { newSkill ->
                    val newList = cvData.skills.toMutableList()
                    newList[index] = newSkill
                    onCVDataChange(cvData.copy(skills = newList))
                },
                onRemove = {
                    val newList = cvData.skills.toMutableList()
                    newList.removeAt(index)
                    onCVDataChange(cvData.copy(skills = newList))
                }
            )
        }
        
        Button(
            onClick = {
                val newList = cvData.skills.toMutableList()
                newList.add(Skill())
                onCVDataChange(cvData.copy(skills = newList))
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("+ Add Skill")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Preview Button
        Button(
            onClick = onPreviewClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Preview CV", fontSize = 18.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun EducationItem(
    education: Education,
    onEducationChange: (Education) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Education Entry", fontWeight = FontWeight.Bold)
                TextButton(onClick = onRemove) {
                    Text("Remove", color = MaterialTheme.colorScheme.error)
                }
            }
            
            OutlinedTextField(
                value = education.degree,
                onValueChange = { onEducationChange(education.copy(degree = it)) },
                label = { Text("Degree") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            
            OutlinedTextField(
                value = education.institution,
                onValueChange = { onEducationChange(education.copy(institution = it)) },
                label = { Text("Institution") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = education.year,
                    onValueChange = { onEducationChange(education.copy(year = it)) },
                    label = { Text("Year") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp, top = 2.dp, bottom = 2.dp)
                )
                
                OutlinedTextField(
                    value = education.gpa,
                    onValueChange = { onEducationChange(education.copy(gpa = it)) },
                    label = { Text("GPA") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp, top = 2.dp, bottom = 2.dp)
                )
            }
        }
    }
}

@Composable
fun ExperienceItem(
    experience: Experience,
    onExperienceChange: (Experience) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Experience Entry", fontWeight = FontWeight.Bold)
                TextButton(onClick = onRemove) {
                    Text("Remove", color = MaterialTheme.colorScheme.error)
                }
            }
            
            OutlinedTextField(
                value = experience.position,
                onValueChange = { onExperienceChange(experience.copy(position = it)) },
                label = { Text("Position") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            
            OutlinedTextField(
                value = experience.company,
                onValueChange = { onExperienceChange(experience.copy(company = it)) },
                label = { Text("Company") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            
            OutlinedTextField(
                value = experience.duration,
                onValueChange = { onExperienceChange(experience.copy(duration = it)) },
                label = { Text("Duration") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            
            OutlinedTextField(
                value = experience.description,
                onValueChange = { onExperienceChange(experience.copy(description = it)) },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                minLines = 2
            )
        }
    }
}

@Composable
fun SkillItem(
    skill: Skill,
    onSkillChange: (Skill) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = skill.name,
                    onValueChange = { onSkillChange(skill.copy(name = it)) },
                    label = { Text("Skill") },
                    modifier = Modifier.weight(1f)
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                OutlinedTextField(
                    value = skill.level,
                    onValueChange = { onSkillChange(skill.copy(level = it)) },
                    label = { Text("Level") },
                    modifier = Modifier.weight(0.7f)
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                TextButton(onClick = onRemove) {
                    Text("×", fontSize = 24.sp, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun CVPreviewScreen(
    cvData: CVData,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Back Button
        TextButton(onClick = onBack) {
            Text("← Back to Edit")
        }
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        // CV Preview Content
        Text(
            text = cvData.personalInfo.name.ifEmpty { "Your Name" },
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        if (cvData.personalInfo.email.isNotEmpty()) {
            Text(
                text = cvData.personalInfo.email,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        if (cvData.personalInfo.phone.isNotEmpty()) {
            Text(
                text = cvData.personalInfo.phone,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        if (cvData.personalInfo.address.isNotEmpty()) {
            Text(
                text = cvData.personalInfo.address,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        if (cvData.personalInfo.summary.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "PROFESSIONAL SUMMARY",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = cvData.personalInfo.summary, fontSize = 14.sp)
        }
        
        if (cvData.education.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "EDUCATION",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            cvData.education.forEach { edu ->
                if (edu.degree.isNotEmpty() || edu.institution.isNotEmpty()) {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        if (edu.degree.isNotEmpty()) {
                            Text(
                                text = edu.degree,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        if (edu.institution.isNotEmpty()) {
                            Text(text = edu.institution, fontSize = 14.sp)
                        }
                        Row {
                            if (edu.year.isNotEmpty()) {
                                Text(text = edu.year, fontSize = 12.sp)
                            }
                            if (edu.gpa.isNotEmpty()) {
                                Text(text = " • GPA: ${edu.gpa}", fontSize = 12.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        
        if (cvData.experience.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "WORK EXPERIENCE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            cvData.experience.forEach { exp ->
                if (exp.position.isNotEmpty() || exp.company.isNotEmpty()) {
                    Column(modifier = Modifier.padding(vertical = 4.dp)) {
                        if (exp.position.isNotEmpty()) {
                            Text(
                                text = exp.position,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        if (exp.company.isNotEmpty()) {
                            Text(text = exp.company, fontSize = 14.sp)
                        }
                        if (exp.duration.isNotEmpty()) {
                            Text(text = exp.duration, fontSize = 12.sp)
                        }
                        if (exp.description.isNotEmpty()) {
                            Text(
                                text = exp.description,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        
        if (cvData.skills.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "SKILLS",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            cvData.skills.forEach { skill ->
                if (skill.name.isNotEmpty()) {
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text(text = "• ${skill.name}", fontSize = 14.sp)
                        if (skill.level.isNotEmpty()) {
                            Text(
                                text = " - ${skill.level}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCVBuilder() {
    LAB_WEEK_09Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            App(navController = navController)
        }
    }
}

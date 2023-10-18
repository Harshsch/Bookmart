package com.BookMart.bookmart

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.BookMart.bookmart.data.ListItem
import com.BookMart.bookmart.ui.theme.DarkPrimaryColor
import com.BookMart.bookmart.ui.theme.DarkSecondaryColor
import com.BookMart.bookmart.ui.theme.DarkSurfaceColor
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksRow(navController: NavHostController, item: ListItem) {
    Column {

        Card(
            onClick = { navController.navigate("BookDisplay/${item.id}")
            },
            modifier = Modifier
                .padding(5.dp)
                .shadow(
                    elevation = 14.dp,
                    spotColor = Color(0x0D06070D),
                    ambientColor = Color(0x0D06070D)
                )
                .width(180.dp)
                .height(290.60001.dp),
            colors = CardDefaults.cardColors(
                containerColor =colorResource(id = R.color.DarkSecondaryColor),
            ),



        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = "image description",
                modifier = Modifier
                    .shadow(
                        elevation = 14.dp,
                        spotColor = Color(0x0D06070D),
                        ambientColor = Color(0x0D06070D)
                    )
                    .width(180.dp)
                    .height(249.60001.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = item.name,
                Modifier,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = colorResource(id = R.color.LightBackgroundColor),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Year ${item.year}, Semester ${item.semester}",
                Modifier
                    .width(155.dp)
                    .height(18.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFF2F2F2),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Department: ${item.department}",
                Modifier
                    .width(155.dp)
                    .height(18.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9D9D9D),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Price: ${item.price}",
                Modifier
                    .width(155.dp)
                    .height(18.dp)
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9D9D9D),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.DarkBackgroundColor),
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id =R.color.DarkBackgroundColor ),
        textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextChanged: (String) -> Unit,
     errorStatus: Boolean = false
) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = labelValue) },


        value = textValue.value,
        onValueChange = {
            textValue.value = it
          onTextChanged(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),

        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = DarkPrimaryColor,
            focusedLabelColor = DarkPrimaryColor,
            cursorColor = DarkSurfaceColor,

        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Composable
fun CheckboxComponent(
    value: String,
     onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            })

        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = DarkPrimaryColor)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = DarkPrimaryColor)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }

    })
}

@Composable
fun ButtonComponent(value: String,
                    onButtonClicked: () -> Unit, isEnabled: Boolean = false
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = colorResource(id=R.color.DarkBgColor)

                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }

    }
}

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = DarkSecondaryColor,
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(R.string.or),
            fontSize = 18.sp,
            color = Color.Black
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = DarkSecondaryColor,
            thickness = 1.dp
        )
    }
}


@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, onTextSelected: (String) -> Unit) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = DarkPrimaryColor)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }

        },
    )
}


@Composable
fun ClickableForgotPasswordTextComponent(email:String) {
    val text = "Forgot your password?"
    val context = LocalContext.current

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = DarkPrimaryColor)) {
            pushStringAnnotation(tag = text, annotation = text)
            append(text)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = {  Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Password reset email sent successfully
                        Toast.makeText(context, "Password reset email sent", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle the error

                        Toast.makeText(context, "Please Verify Email", Toast.LENGTH_SHORT).show()
                    }
                }
                }

    )
}
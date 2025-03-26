@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.droidchat.ui.components

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.droidchat.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import br.com.droidchat.DroidChatFileProvider
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun ProfilePictureOptionsModalBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onPictureSelected: (Uri) -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState(),
    context: Context = LocalContext.current,
) {
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let(onPictureSelected)
            onDismissRequest()
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                photoUri?.let { onPictureSelected(it) }
            }
        }
    )

    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        ProfileBottomSheetOptionItem(
            iconRes = R.drawable.ic_photo_camera,
            label = R.string.common_take_photo,
            onClick = {
                photoUri = DroidChatFileProvider.getImageUri(context.applicationContext)
                photoUri?.let { cameraLauncher.launch(it) }
            }
        )
        ProfileBottomSheetOptionItem(
            iconRes = R.drawable.ic_photo_library,
            label = R.string.common_upload_photo,
            onClick = {
                imagePicker.launch("image/*")
            }
        )
    }
}

@Composable
private fun ProfileBottomSheetOptionItem(
    @StringRes label: Int,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp)
            .clickable { onClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = stringResource(label),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun Preview() {
    val sheetState = SheetState(
        skipHiddenState = false,
        skipPartiallyExpanded = false,
        density = Density(LocalContext.current),
        initialValue = SheetValue.Expanded
    )

    DroidChatTheme {
        ProfilePictureOptionsModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {}
        )
    }
}
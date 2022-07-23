import me.danbe.common.App
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.WindowConstants

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {

        val window = JFrame()
        window.title = "You Tube"
        window.setSize(800, 600)
        window.minimumSize = Dimension(800,600)

        val composePanel = ComposePanel()

        composePanel.setContent { MaterialTheme { App() } }

        window.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        window.contentPane.add(composePanel, BorderLayout.CENTER)
        window.setLocationRelativeTo(null)
        window.isVisible = true
    }
}
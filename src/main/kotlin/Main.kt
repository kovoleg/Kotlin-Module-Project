import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val menuManager = MenuManager(scanner)
    val archives = mutableListOf<Archive>()

    // Главная функция, ответственная за навигацию по приложению
    showArchiveList(archives, menuManager)

    scanner.close()
    println("Работа приложения завершена.")
}
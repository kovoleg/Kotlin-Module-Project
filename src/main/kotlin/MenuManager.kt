import java.util.Scanner

// Реализуем работу с ЛЮБЫМ меню (меню может быть: начальное, на этапе создания замтеки и тд)
class MenuManager(private val scanner: Scanner) {
    fun showMenu(title: String, items: List<MenuItem>) { // Функция для отображения ЛЮБОГО меню. items - пункты меню.
        while (true) {
            println("\n $title")
            items.forEachIndexed { index, item ->
                println("$index. ${item.title}")
            }

            print("Введите номер пункта: ")
            val input = scanner.nextLine().toIntOrNull()// Проверяем число ли это

            if (input == null) { // Введены буквы
                println("Ошибка: Введите цифру, соответствующую пункту меню.")
                continue
            }

            else if (input !in items.indices) { // Введены цифры вне диапазона
                println("Ошибка: Такого пункта меню не существует. Введите цифру от 0 до ${items.size - 1}.")
                continue
            }

            else {
                items[input].action() // Реализуем требуемое действие в зависимости от типа (архив/список)
                return
            }
        }
    }

    fun requestNonEmptyInput(prompt: String): String { // Нужно проверить, что строка не пустая
        while (true) {
            print("$prompt: ")
            val input = scanner.nextLine()
            if (input.isNotBlank()) {
                return input
            } else {
                println("Ошибка: Ввод не может быть пустым.")
            }
        }
    }
}
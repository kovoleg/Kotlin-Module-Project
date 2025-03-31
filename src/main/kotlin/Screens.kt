import kotlin.system.exitProcess

// Главная функция, которая показывает все меню, закрепляя за каждым пунктом лямбда функцию
fun showArchiveList(archives: MutableList<Archive>, menuManager: MenuManager) {
    while (true) {
        val menuItems = mutableListOf<MenuItem>()

        menuItems.add(MenuItem("Создать архив") {
            createArchive(archives, menuManager)
        })

        // Добавляем существующие архивы в меню и каждого такого архива будет лямбда функция - показать список заметок
        archives.forEach{ archive -> menuItems.add(MenuItem(archive.name) {
            showNoteList(archive, menuManager)
        })
        }

        // Выход
        menuItems.add(MenuItem("Выход") {
            println("Выход из программы...")
            exitProcess(0)
        })

        menuManager.showMenu("Главное меню: Список архивов", menuItems)
    }
}

// Функция для создания нового архива
fun createArchive(archives: MutableList<Archive>, menuManager: MenuManager) {
    println("\n Создание нового архива...")
    val archiveName = menuManager.requestNonEmptyInput("Введите название архива")
    archives.add(Archive(name = archiveName))
    println("Архив '$archiveName' успешно создан!")
}

// Функция для показа списка заметок
fun showNoteList(archive: Archive, menuManager: MenuManager) {
    var shouldContinue = true
    while (shouldContinue) {
        val menuItems = mutableListOf<MenuItem>()

        menuItems.add(MenuItem("Создать заметку") {
            createNote(archive, menuManager)
        })

        archive.notes.forEach{ note ->  menuItems.add(MenuItem(note.name) {
            viewNote(note, menuManager)
        })
        }

        menuItems.add(MenuItem("Назад") {
            shouldContinue = false // Выходим из цикла на предыдущий экран
        })

        menuManager.showMenu("Список заметок в архиве: ${archive.name}", menuItems)
    }
}

// Функция для создания новой заметки
fun createNote(archive: Archive, menuManager: MenuManager) {
    println("\n Создание новой заметки в архиве '${archive.name}'... ")
    val noteName = menuManager.requestNonEmptyInput("Введите название заметки:")
    val noteContent = menuManager.requestNonEmptyInput("Введите текст заметки:")

    archive.notes.add(Note(name = noteName, content = noteContent))
    println("Заметка '$noteName' успешно создана в архиве '${archive.name}'!")
}

// Функция для просмотра содержимого заметки
fun viewNote(note: Note, menuManager: MenuManager) {
    println("\n Просмотр заметки... ")
    println("Название заметки: ${note.name}")
    println("Содержание заметки:")
    println(note.content)
    println("===Конец заметки===")

    val menuItems = listOf(
        MenuItem("Назад") { // Нужен только пункт назад
        }
    )

    menuManager.showMenu("Заметка: ${note.name}", menuItems)
}
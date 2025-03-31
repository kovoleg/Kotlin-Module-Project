abstract class NamedItem(open var name: String)

data class Note( // записка содержит название и содержимое типа string
    override var name: String,
    var content: String
) : NamedItem(name)

data class Archive( // архив содержит название и список со списками :D
    override var name: String,
    val notes: MutableList<Note> = mutableListOf()
) : NamedItem(name)
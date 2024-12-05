package at.kocmana.helper

class Parser {

    fun readFile(filename: String): List<String> =
        this::class.java.getResourceAsStream(filename)!!.bufferedReader().readLines()

    fun <T> readAndModify(filename: String, modifier: (String) -> T): List<T> =
        readFile(filename).map(modifier)

    fun readToIntLists(filename: String, splitter: String): List<List<Int>> =
        readFile(filename)
            .asSequence()
            .map{it.split(splitter)}
            .map{it.map(Integer::valueOf)}
            .toList()

    fun readFileTo2dArray(fileName: String) =
        readFile(fileName)
            .map{it.chunked(1).toTypedArray()}
            .toTypedArray()
}
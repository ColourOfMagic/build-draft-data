package builder

import builder.aggregator.AggregatorService

class App {

    fun run() {
        aggregateSet(5)
        aggregateSet(25)
    }

    private fun aggregateSet(fileSize: Int) {
        val setName = "set$fileSize"
        (1..10).forEach { i ->
            aggregate("$setName/$setName-$i.json", fileSize)
        }
    }

    private fun aggregate(fileName: String, elementsCount: Int) {
        println(">>>> Aggregate $fileName-[$elementsCount]")
        AggregatorService.aggregateToFile(fileName, elementsCount)
        println("<<<< End $fileName-[$elementsCount]")
    }
}
package kz.almaty.satbayevuniversity.ui.references.history

data class TestData(
        var reference_id: String,
        var reference_date: String,
        val reference_type: String,
        val reference_status: Int,
        val reference_responce: String,
        val inList: Boolean
)

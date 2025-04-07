package hnu.multimedia.androiddh.week5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExamViewModel : ViewModel() {

    private val _examModelList = MutableLiveData(mutableListOf(
        ExamModel("First Exam", "2025.03.15", "최선을 다하자!!", "90501", "1과 ~ 2과"),
        ExamModel("Midterm Exam", "2025.05.10", "재수강 할까?", "90502", "3과 ~ 4과"),
        ExamModel("Final Exam", "2025.06.15", "내가 제일 마지막에 끝낼 듯..", "90503", "5과 ~ 6과"),
    ))
    val examModelList: LiveData<MutableList<ExamModel>> get() = _examModelList

    fun addExam(examModel: ExamModel) {
        _examModelList.value?.let {
            it.add(examModel)
            _examModelList.value = it
        }
    }
}
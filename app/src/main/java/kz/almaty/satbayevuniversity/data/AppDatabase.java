package kz.almaty.satbayevuniversity.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kz.almaty.satbayevuniversity.data.entity.AccountEntity;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.data.entity.academic.ResponseJournal;
import kz.almaty.satbayevuniversity.data.entity.grade.attestation.Attestation;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.SemestersItem;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDisciplineGroup1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.Semesters1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline.DeferredDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline.DeferredDisciplineGroup1;
import kz.almaty.satbayevuniversity.data.entity.notification.Notification;
import kz.almaty.satbayevuniversity.data.entity.schedule.Exam;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.entity.umkd.Umkd;
import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.Semesters;
import kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes.DeferedDiscipline;

@Database(entities = {AccountEntity.class, ResponseJournal.class, Schedule.class, Exam.class, Attestation.class, SemestersItem.class, Umkd.class, Language.class, Notification.class, Semesters1.class, DeferredDisciplineGroup1.class, DeferredDiscipline1.class}, version = 17, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
}

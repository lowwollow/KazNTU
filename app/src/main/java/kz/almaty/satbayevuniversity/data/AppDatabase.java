package kz.almaty.satbayevuniversity.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kz.almaty.satbayevuniversity.data.entity.AccountEntity;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.data.entity.academic.ResponseJournal;
import kz.almaty.satbayevuniversity.data.entity.grade.attestation.Attestation;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.SemestersItem;
import kz.almaty.satbayevuniversity.data.entity.notification.Notification;
import kz.almaty.satbayevuniversity.data.entity.schedule.Exam;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.entity.umkd.Umkd;

@Database(entities = {AccountEntity.class, ResponseJournal.class, Schedule.class, Exam.class, Attestation.class, SemestersItem.class, Umkd.class, Language.class, Notification.class}, version = 9, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
}
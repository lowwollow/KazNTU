//package com.example.kazntu;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.List;
//
//import kz.almaty.satbayevuniversity.data.AppDatabase;
//import kz.almaty.satbayevuniversity.data.entity.admission.User;
//
//public class TestDatabase {
//    private User user;
//    private AppDatabase db;
//
//    @Before
//    public void createDb() {
//        Context context = ApplicationProvider.getApplicationContext();
//        db = Room.inMemoryDatabaseBuilder(context, TestDatabase.class).build();
//        user = db.accountDao().getAccountEntity();
//    }
//
//    @After
//    public void closeDb() throws IOException {
//        db.close();
//    }
//
//    @Test
//    public void writeUserAndReadInList() throws Exception {
//        User user = TestUtil.createUser(3);
//        user.setName("george");
//        userDao.insert(user);
//        List<User> byName = userDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
//    }
//}

package com.example.franc.mygest.persistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by franc on 23/04/2018.
 */

public class MovimentoViewModel extends AndroidViewModel {

    private MovimentoRepo mRepository;

    private LiveData<List<EntityMovimento>> mAllMovimento;

    public MovimentoViewModel(Application application) {
        super(application);
        mRepository = new MovimentoRepo(application);
        mAllMovimento = mRepository.getAllMovimento();
    }

    public void deleteTransactionById(int id){
        mRepository.deleteTransactionById(id);
    }
    public LiveData<List<EntityMovimento>> getAllWords() { return mAllMovimento; }
/*
    public LiveData<List<String>> getAllMovimentoDist(java.util.Date upTo) { return mRepository.getAllMovimentoUpTo(upTo); }
*/

    public LiveData<List<EntityMovimento>> getAllMovimentoDistByAccount(java.util.Date upTo, String account) { return mRepository.getAllMovimentoUpToByAccount(upTo, account); }


    public void insert(String beneficiario, String importo, java.util.Date scadenza, String conto, @Nullable final java.util.Date endDate, String recurrence) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(scadenza);

        if(recurrence.equalsIgnoreCase("NESSUNA")){
            EntityMovimento mov = new EntityMovimento(beneficiario, importo, cal.getTime(), conto, endDate);

            mRepository.insert(mov);
        }
        else if(recurrence.equalsIgnoreCase("DAILY")){
            while (cal.getTime().before(endDate)){
                EntityMovimento mov = new EntityMovimento(beneficiario, importo, cal.getTime(), conto, endDate);
                mRepository.insert(mov);
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
        else if(recurrence.equalsIgnoreCase("WEEKLY")){
            while (cal.getTime().before(endDate)){
                EntityMovimento mov = new EntityMovimento(beneficiario, importo, cal.getTime(), conto, endDate);
                mRepository.insert(mov);
                cal.add(Calendar.WEEK_OF_YEAR, 1);
            }
        }
        else if(recurrence.equalsIgnoreCase("MONTHLY")){
            while (cal.getTime().before(endDate)){
                EntityMovimento mov = new EntityMovimento(beneficiario, importo, cal.getTime(), conto, endDate);
                mRepository.insert(mov);
                cal.add(Calendar.MONTH, 1);
            }
        }
        else if(recurrence.equalsIgnoreCase("YEARLY")){
            while (cal.getTime().before(endDate)){
                EntityMovimento mov = new EntityMovimento(beneficiario, importo, cal.getTime(), conto, endDate);
                mRepository.insert(mov);
                cal.add(Calendar.YEAR, 1);
            }
        }



/*
        mRepository.insert(movimento);
*/
    }
}

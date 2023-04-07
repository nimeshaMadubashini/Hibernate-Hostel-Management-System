package lk.ijse.hibernate.dao.custome;

import lk.ijse.hibernate.dao.CrudDao;
import lk.ijse.hibernate.entity.Reservation;

public interface ReservationDAO extends CrudDao<Reservation,String> {
    String getReservationId() throws Exception;
}

package lk.ijse.hibernate.bo.custome;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.entity.Reservation;

import java.util.List;

public interface KeyPaymentBO extends SuperBO {
     boolean updatePayment(String resId) throws Exception;
     List<Object[]> getPendingKeyPayments() throws Exception;
}

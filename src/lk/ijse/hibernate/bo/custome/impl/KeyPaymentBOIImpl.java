package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.KeyPaymentBO;
import lk.ijse.hibernate.bo.custome.ReservationBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.QueryDAO;
import lk.ijse.hibernate.dao.custome.ReservationDAO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;

import java.util.List;

public class KeyPaymentBOIImpl implements KeyPaymentBO {
    ReservationDAO resDAO= (ReservationDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.RESERVATON);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.QUERY);

    @Override
    public boolean updatePayment(String resId ) throws Exception {
        Reservation reservation = resDAO.get(resId);
        reservation.setStatus("Payed");
        return resDAO.update(reservation);
    }

    @Override
    public List<Object[]> getPendingKeyPayments() throws Exception {
        return queryDAO.getPendingKeyPayments();
    }
}

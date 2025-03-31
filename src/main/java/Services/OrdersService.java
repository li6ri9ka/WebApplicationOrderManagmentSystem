package Services;

import DTO.OrderDTO;
import Model.Orders;
import Repositories.OrderRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MappingUntils mappingUntils;


    public List<OrderDTO> findAll(){
        return orderRepository.findAll().stream().map(mappingUntils::mappingOrderDTO).toList();
    }

    public Orders findById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public Orders addOrder(Orders order){
        return orderRepository.save(order);
    }

    public Orders updateOrder(Long id, int quantity,Orders order){
        if(orderRepository.existsById(id)){
            order.setId(id);
            order.setQuantity(quantity);
            return orderRepository.save(order);
        }
        else {
            throw new RuntimeException("Order not found");
        }
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

}

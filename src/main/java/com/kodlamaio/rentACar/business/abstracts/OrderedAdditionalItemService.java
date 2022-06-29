package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.request.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.orderedAdditionalItems.DeleteOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.request.orderedAdditionalItems.UpdateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItems.GetAllOrderedAdditionalItemsResponse;
import com.kodlamaio.rentACar.business.response.orderedAdditionalItems.ReadOrderedAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;

public interface OrderedAdditionalItemService {

	Result add(CreateOrderedAdditionalItemRequest createOrderedAdditionalItemRequest);

	Result update(UpdateOrderedAdditionalItemRequest updateOrderedAdditionalItemRequest);

	Result delete(DeleteOrderedAdditionalItemRequest deleteOrderedAdditionalItemRequest);

	DataResult<ReadOrderedAdditionalItemResponse> getById(int id);

	DataResult<List<GetAllOrderedAdditionalItemsResponse>> getAll();

	public List<OrderedAdditionalItem> getByOrderedAdditionalItemsId(int id);

}

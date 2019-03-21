package com.example.ecommerceseller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerceseller.R;
import com.example.ecommerceseller.model.Order;
import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersHolder> {

    private Context context;
    private ArrayList<Order> ordersList;

    public OrdersAdapter(Context context, ArrayList<Order> ordersList) {
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.order_row,parent,false);
        return new OrdersHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersHolder holder, int position) {
        Order order=ordersList.get(position);
        holder.orderNum.setText(String.valueOf(order.getOrder_id()));
        holder.orderDate.setText(order.getOrder_date());
        holder.totalPrice.setText(String.valueOf(order.getTotalPrice()));
        holder.totalItems.setText(String.valueOf(order.getOrderItems().size()));

        OrderItemsAdapter orderItemsAdapter=new OrderItemsAdapter(context,order.getOrderItems());
        holder.orderItemsRecycler.setAdapter(orderItemsAdapter);
        holder.orderItemsRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.orderItemsRecycler.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    class OrdersHolder extends RecyclerView.ViewHolder{
        TextView orderNum,orderDate;
        TextView totalItems,totalPrice;
        Button handleState;
        ImageView expand;
        RecyclerView orderItemsRecycler;
        public OrdersHolder(@NonNull View itemView) {
            super(itemView);
            orderNum=itemView.findViewById(R.id.order_num);
            orderDate=itemView.findViewById(R.id.order_date);
            totalItems=itemView.findViewById(R.id.total_items);
            totalPrice=itemView.findViewById(R.id.total_price);
            handleState=itemView.findViewById(R.id.handle_state);
            expand=itemView.findViewById(R.id.expand);
            orderItemsRecycler=itemView.findViewById(R.id.orderItems_recylerView);

            expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orderItemsRecycler.getVisibility()==View.GONE)
                        orderItemsRecycler.setVisibility(View.VISIBLE);
                    else
                        orderItemsRecycler.setVisibility(View.GONE);
                }
            });
        }
    }
}

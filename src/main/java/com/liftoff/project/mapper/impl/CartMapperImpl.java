package com.liftoff.project.mapper.impl;

import com.liftoff.project.controller.cart.response.CartItemResponseDTO;
import com.liftoff.project.controller.cart.response.CartResponseDTO;
import com.liftoff.project.mapper.CartMapper;
import com.liftoff.project.mapper.ProductMapper;
import com.liftoff.project.model.Cart;
import com.liftoff.project.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapperImpl implements CartMapper {

    private final ProductMapper productMapper;

    @Override
    public CartResponseDTO mapCartToCartResponseDTO(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setUuid(cart.getUuid());
        cartResponseDTO.setCartItems(cart.getCartItems().stream()
                .map(cartItem -> {
                    CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
                    cartItemResponseDTO.setProduct(productMapper.mapEntityToResponse(cartItem.getProduct()));
                    cartItemResponseDTO.setQuantity(cartItem.getQuantity());
                    return cartItemResponseDTO;
                })
                .toList());
        cartResponseDTO.setTotalPrice(cart.getTotalPrice());
        cartResponseDTO.setTotalQuantity(cart.getTotalQuantity());

        return cartResponseDTO;
    }

    @Override
    public CartItemResponseDTO mapCartItemToCartItemResponseDTO(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
        cartItemResponseDTO.setProduct(productMapper.mapEntityToResponse(cartItem.getProduct()));
        cartItemResponseDTO.setQuantity(cartItem.getQuantity());

        return cartItemResponseDTO;
    }

}

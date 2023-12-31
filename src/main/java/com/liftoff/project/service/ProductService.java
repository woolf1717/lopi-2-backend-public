package com.liftoff.project.service;

import com.liftoff.project.controller.product.request.PaginationParameterRequestDTO;
import com.liftoff.project.controller.product.request.ProductRequestDTO;
import com.liftoff.project.controller.product.response.PaginatedProductResponseDTO;
import com.liftoff.project.controller.product.response.ProductResponseDTO;
import com.liftoff.project.exception.BusinessException;
import com.liftoff.project.exception.TechnicalException;
import com.liftoff.project.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    /**
     * Retrieves a paginated list of products.
     *
     * @param pageable The pagination information specifying the page number, page size, and sorting order.
     * @return A {@link PaginatedProductResponseDTO} containing the paginated list of products.
     */
    PaginatedProductResponseDTO getProducts(Pageable pageable);

    /**
     * Retrieves a specific product by its UUID as the ProductResponseDTO object.
     *
     * @param productUuid The UUID of the product to be retrieved.
     * @return The ProductResponseDTO object representing the product with specified UUID.
     * @throws BusinessException If the product with the given UUID is not found.
     */
    ProductResponseDTO getProductByUuid(UUID productUuid);

    /**
     * Retrieves a list of products that belong to the category with the specified UUID.
     *
     * @param categoryUuid The UUID of the category to retrieve products for.
     * @return A list of product response DTOs representing the products in the category.
     * @throws BusinessException If the category with the given UUID is not found.
     */
    List<ProductResponseDTO> getProductsByCategoryUuid(UUID categoryUuid);

    /**
     * Retrieves a list of n most recently added active products.
     *
     * @param n The number of most recent products to fetch.
     * @return The list of ProductResponseDTO objects representing the most recently added active products.
     * The list may be empty if there are no active products.
     */

    List<ProductResponseDTO> getNRecentAddedActiveProducts(int n);

    /**
     * Adds a new product based on the provided ProductRequestDTO object
     * and returns the created product as the ProductResponseDTO object.
     *
     * @param productRequestDTO The ProductRequestDOT object containing the details of the new product.
     * @return The ProductResponseDTO object representing the newly added product.
     */
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);

    /**
     * Updates an existing product with the given UUID based on the provided product request data.
     *
     * @param productUuid       The UUID of the product to be updated.
     * @param productRequestDTO The ProductRequestDTO object containing the updated details of the product.
     * @return The ProductResponseDTO object representing the updated product.
     * @throws BusinessException If the product with the given UUID is not found.
     */
    ProductResponseDTO updateProductByUuid(UUID productUuid, ProductRequestDTO productRequestDTO);

    /**
     * Deletes a product with the specified UUID.
     *
     * @param productUuId The UUID of the product to be deleted.
     * @throws BusinessException If the product with given UUID is not found.
     */
    void deleteProductByUuId(UUID productUuId);

    /**
     * Activate an existing product.
     *
     * @param product The product to be updated.
     * @return The ProductResponseDTO object representing the activated product.
     * @throws BusinessException If the product with the given UUID is not found.
     */
    ProductResponseDTO activateProduct(ProductResponseDTO product);

    /**
     * Adds an image to a product based on its UUID.
     *
     * @param productUuid The UUID of the product to which the image will be added.
     * @param imageFile   The image file to be added to the product.
     * @return A ResponseEntity containing the updated ProductResponseDTO with the added image.
     * @throws BusinessException If the product with the specified UUID is not found.
     * @throws IOException       If an error occurs while processing the image file.
     */
    ProductResponseDTO addImageToProduct(UUID productUuid, MultipartFile imageFile) throws IOException;

    /**
     * Deletes the specified image URL from the product with the provided UUID.
     *
     * @param productUuid The UUID of the product to delete the image from.
     * @param imageUrl    The URL of the image to be deleted from the product.
     * @return A {@link ProductResponseDTO} representing the updated product after image deletion.
     * @throws TechnicalException If the specified image URL is not found in the product.
     * @throws BusinessException  If the product with the given UUID is not found.
     */
    ProductResponseDTO deleteImageByUrlFromProduct(UUID productUuid, String imageUrl);

    /**
     * Retrieves a product entity by its unique identifier.
     *
     * @param productUuid The unique identifier (UUID) of the product to retrieve.
     * @return The product entity if found.
     * @throws BusinessException If no product is found with the given UUID.
     */
    Product getProductEntityByUuid(UUID productUuid);

    /**
     * Retrieves a paginated and sorted list of products by category.
     *
     * @param categoryUuid        The UUID of the category for which products are to be retrieved.
     * @param paginationParameter The parameters for pagination and sorting.
     * @return A Page containing the sorted and paginated list of ProductResponseDTO objects.
     * @throws BusinessException If the category with the specified UUID is not found.
     */
    Page<ProductResponseDTO> getProductsByCategoryAndSort(
            UUID categoryUuid,
            PaginationParameterRequestDTO paginationParameter);

}
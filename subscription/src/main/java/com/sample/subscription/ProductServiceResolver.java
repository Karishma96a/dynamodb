package com.sample.subscription;

import com.sample.subscription.exception.UnsupportedProductException;
import com.sample.subscription.external.service.*;
import com.sample.subscription.model.Product;
import com.sample.subscription.model.ProductType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductServiceResolver {

    @Autowired
    private final QBAccountingService qbAccountingController;

    @Autowired
    private final QBPayrollService qbPayrollController;

    @Autowired
    private final QBPaymentsService qbPaymentsController;

    @Autowired
    private final TSheetsService tSheetsController;

    public IValidationService getProductServiceResolver(final Product product) {
        if (ProductType.QB_ACCOUNTING.equals(product.getProductType())) {
            return qbAccountingController;
        }

        if (ProductType.QB_PAYROLL.equals(product.getProductType())) {
            return qbPayrollController;
        }

        if (ProductType.QB_PAYMENTS.equals(product.getProductType())) {
            return qbPaymentsController;
        }

        if (ProductType.TSHEETS.equals(product.getProductType())) {
            return tSheetsController;
        }

        throw new UnsupportedProductException(product.getProductType() + " product type is not supported yet");
    }
}

package cl.playground.triggersapp.commands;

import cl.playground.triggersapp.entities.QuotationItem;

import java.util.List;

public class CreateQuotationCommand {
    private List<QuotationItem> items;

    public CreateQuotationCommand(List<QuotationItem> items) {
        this.items = items;
    }

    public List<QuotationItem> getItems() {
        return items;
    }
}


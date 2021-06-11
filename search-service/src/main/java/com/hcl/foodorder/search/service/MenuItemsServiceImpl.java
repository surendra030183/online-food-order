package com.hcl.foodorder.search.service;

import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import com.hcl.foodorder.search.model.MenuItem;


@Service
public class MenuItemsServiceImpl implements MenuItemsService{

    private static final Logger logger = LoggerFactory.getLogger(MenuItemsServiceImpl.class);

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Value("${elasticsearch.items.type}")
    private String itemTypeName;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    /**
     * Save the new menuItems details in Repository
     *
     * @param menuItem
     * @return
     */
    @Override
    public String save(MenuItem menuItem) {
        logger.info("Save new menuItems Details in Repository ");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(menuItem.getId().toString())
                .withObject(menuItem)
                .build();
        return elasticsearchOperations.index(indexQuery,IndexCoordinates.of(indexName));
    }

    /**
     * Search the menuItems details in Repository based on the name and Quantity
     *
     * @param name
     * @param qty
     * @return
     */
    public List<MenuItem> searchMultiField(String name, int qty) {
        logger.info("Invoking searchMultiField method based on name " +name + "Quantity "+qty);
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", name))
                .must(QueryBuilders.matchQuery("quantity", qty));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        SearchHits<MenuItem> items = elasticsearchOperations.search(nativeSearchQuery, MenuItem.class, IndexCoordinates.of(indexName));
        return items.stream().map(s -> s.getContent()).collect(Collectors.toList());
    }

    /**
     * Search the menuItems details in Repository based on the name and wildcard
     *
     * @param name
     * @return
     */
    public List<MenuItem> getMenuNameSearchData(String name) {
        logger.info("Invoking getMenuNameSearchData method based on name " +name);
        String search = ".*" + name + ".*";
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withFilter(QueryBuilders.regexpQuery("name", search)).build();
        SearchHits<MenuItem> items = elasticsearchOperations.search(nativeSearchQuery, MenuItem.class, IndexCoordinates.of(indexName));
        return items.stream().map(s -> s.getContent()).collect(Collectors.toList());
    }

    /**
     * Search the menuItems details in Repository based on the restaurantId Or MenuId
     *
     * @param id
     * @return
     */
    public List<MenuItem> multiMatchQuery(Long id) {
        logger.info("Invoking multiMatchQuery method based on restaurantIdOrMenuId " +id);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(id)
                .field("restaurantId").field("id").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
        SearchHits<MenuItem> items = elasticsearchOperations.search(nativeSearchQuery, MenuItem.class, IndexCoordinates.of(indexName));
        return items.stream().map(s -> s.getContent()).collect(Collectors.toList());
    }

    /**
     * Search the menuItems details in Repository based on the price range
     *
     * @param itemPriceMin
     * @param itemPriceMax
     * @return
     */
    public List<MenuItem> getMenuItemsByPriceRange(final Double itemPriceMin, final Double itemPriceMax) {
        logger.info("Invoking multiMatchQuery method based on price range");
        Criteria criteria = new Criteria("price")
                .greaterThan(itemPriceMin)
                .lessThan(itemPriceMax);

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<MenuItem> items = elasticsearchOperations.search(searchQuery, MenuItem.class, IndexCoordinates.of(indexName));
        return items.stream().map(s -> s.getContent()).collect(Collectors.toList());
    }

    /**
     * Search the menuItems details in Repository based on the MenuItemsByDescriptions
     *
     * @param description
     * @return
     */
    public List<MenuItem> getMenuItemsByDescriptions(final String description) {
        logger.info("Invoking getMenuItemsByDescriptions method based on description "+description);
        Query searchQuery = new StringQuery(
                "{\"match\":{\"description\":{\"query\":\""+ description + "\"}}}\"");

        SearchHits<MenuItem> items = elasticsearchOperations.search(searchQuery, MenuItem.class, IndexCoordinates.of(indexName));
        return items.stream().map(s -> s.getContent()).collect(Collectors.toList());
    }

}

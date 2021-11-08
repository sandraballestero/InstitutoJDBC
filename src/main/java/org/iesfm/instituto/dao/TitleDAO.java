package org.iesfm.instituto.dao;

import org.iesfm.instituto.Title;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TitleDAO {

    private final static String SELECT_TITLE_BY_NAME =
            "SELECT * FROM title WHERE title_name=:name";
    private final static String SELECT_TITLES = "SELECT * FROM title";
    private final static String SELECT_FAMILY_TITLES =
            "SELECT * FROM title WHERE family=:family";

    private final static String INSERT_TITLE = "INSERT INTO title(" +
            "   title_name, " +
            "   title_level, " +
            "   family, " +
            "   title_description" +
            ") " +
            "VALUES(" +
            "   :name," +
            "   :level," +
            "   :family," +
            "   :description" +
            ")";

    private static final RowMapper<Title> TITLE_ROW_MAPPER =
            (rs, rownum) ->
                    new Title(
                            rs.getInt("title_id"),
                            rs.getString("title_name"),
                            rs.getString("title_level"),
                            rs.getString("family"),
                            rs.getString("title_description")
                    );

    private NamedParameterJdbcTemplate jdbc;

    public TitleDAO(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insert(Title title) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", title.getName());
        params.put("level", title.getLevel());
        params.put("family", title.getFamily());
        params.put("description", title.getDescription());
        jdbc.update(INSERT_TITLE, params);
    }

    public Title get(String name) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            return jdbc.queryForObject(
                    SELECT_TITLE_BY_NAME,
                    params,
                    TITLE_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Title> list() {
        Map<String, Object> params = new HashMap<>();
        // ResultSet Title(title_id, title_name, title_level, family, title_description)
        // row1 (1,"DAM", "GS", "Informatica", "Aprender a hacer....")
        // row2 (2,"DAW", "GS", "Informatica", "Aprender a hacer....")
        // row3 (3,"ADM", "GS", "Administración", "Aprender a hacer....")
        return jdbc.query(
                SELECT_TITLES,
                params,
                // (rs(1,"DAM", "GS", "Informatica", "Aprender a hacer...."), 1)
                // (rs(2,"DAW", "GS", "Informatica", "Aprender a hacer...."), 2)
                // (rs(3,"ADM", "GS", "Administración", "Aprender a hacer...."), 3)
                TITLE_ROW_MAPPER
        );
    }

    public List<Title> list(String family) {
        Map<String, Object> params = new HashMap<>();
        params.put("family", family);
        return jdbc.query(
                SELECT_FAMILY_TITLES,
                params,
                TITLE_ROW_MAPPER
        );
    }

}

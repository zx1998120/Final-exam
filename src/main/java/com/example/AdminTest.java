package com.example;

import com.example.functions.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminTest {
    private Connection connection;
    private Admin admin;

    @BeforeEach
    void setUp() {
        connection = mock(Connection.class);
        admin = new Admin(connection);
    }

    @Test
    void testInvalidAdminLogin() throws SQLException {
        Scanner scanner = new Scanner("00000\n");
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        assertDoesNotThrow(() -> admin.administratorLogin(scanner));
    }

    @Test
    void testCreateNewAccount() throws SQLException {
        Scanner scanner = new Scanner("testLogin\n123456\nTest Holder\n100.0\nActive\n");
        PreparedStatement statement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> admin.createNewAccount(scanner));
    }

    @Test
    void testDeleteAccount() throws SQLException {
        Scanner scanner = new Scanner("123\n");
        PreparedStatement statement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> admin.deleteAccount(scanner));
    }

    @Test
    void testUpdateAccount() throws SQLException {
        Scanner scanner = new Scanner("123\nNew Name\n200.0\nActive\n");
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(statement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> admin.updateAccount(scanner));
    }

    @Test
    void testSearchForAccount() throws SQLException {
        Scanner scanner = new Scanner("123\n");
        PreparedStatement statement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        assertDoesNotThrow(() -> admin.searchForAccount(scanner));
    }
}
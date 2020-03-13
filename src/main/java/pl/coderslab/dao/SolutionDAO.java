package pl.coderslab.dao;



import add.DbUtil;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionDAO {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution (created, updated) VALUES (null, null)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET users_id = ?, created = NOW() where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY =
            "SELECT * FROM solution";
    private static final String UPDATE_SOLUTION_TO_EXERCISE_QUERY =
            "UPDATE solution SET exercise_id = ? WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID =
            "SELECT * FROM solution WHERE users_id = ?";
    private static final String FIND_ALL_SOLUTIONS_BY_EXERCISE_ID =
            "SELECT * FROM solution WHERE exercise_id = ?";
    private static final String UPDATE_DESCRIPTION_BY_EXERCISE_ID =
            "UPDATE solution SET description = ?, updated = NOW() WHERE exercise_id = ? AND users_id = ?";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID_AND_EXERCISE_ID =
            "SELECT * FROM solution WHERE users_id = ? AND exercise_id = ?";
    private static final String READ_RECENT_QUERY = "SELECT id, created, updated, description, users_id FROM solution ORDER BY updated LIMIT ?;";

    private PreparedStatement statement;
    private static ExerciseDAO exerciseDAO = new ExerciseDAO();
    private static UserDAO userDAO = new UserDAO();

    public Solution create(Solution solution) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            return null;
        }
    }

    public void insertExerciseIntoSolution(Solution solution, int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(UPDATE_SOLUTION_TO_EXERCISE_QUERY);
            statement.setInt(1, userInput);
            statement.setInt(2, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
    }

    public void resolveExercise(User user, String description, Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(UPDATE_DESCRIPTION_BY_EXERCISE_ID);
            statement.setString(1, description);
            statement.setInt(2, exercise.getId());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
    }

    public Exercise isExerciseAssignedToUser(User user, Exercise exercise) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID_AND_EXERCISE_ID);
            statement.setInt(1, user.getId());
            statement.setInt(2, exercise.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            } else {
                return exercise;
            }
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
            return null;
        }
    }


    public void assignSolutionToTheUser(User user, int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setInt(1, user.getId());
            statement.setInt(2, userInput);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
    }

    public Solution read(int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, userInput);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                return solution;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }

    public int delete(int id) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
        return -1;
    }

    public List<Solution> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            List<Solution> solutions = new ArrayList<>();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTime("created"));
                solution.setUpdated(resultSet.getTime("updated"));
                solution.setDescription(resultSet.getString("description"));
//TAK LEPIEJ CZY
                if (resultSet.getInt("users_id") != 0) {
                    solution.setUsers_id(userDAO.read(resultSet.getInt("users_id")));
                } else {
                    solution.setUsers_id(null);
                }
/*                int userGroupId = resultSet.getInt("user_group_id");
                UserGroup userGroup = userGroupDAO.read(userGroupId);
                user.setUserGroup(userGroup);*/
//MOZE TAK??
                int exerciseID = resultSet.getInt("exercise_id");
                Exercise exercise = exerciseDAO.read(exerciseID);
                if (exercise == null) {
                    solution.setExercise_id(null);
                } else {
                    solution.setExercise_id(exerciseDAO.read(resultSet.getInt("exercise_id")));
                }

                solutions.add(solution);
            }
            return solutions;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }

    private Solution[] addToArray(Solution[] solutions, Solution solution) {
        Solution[] tempSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tempSolutions[tempSolutions.length - 1] = solution;
        return tempSolutions;
    }
    public List<Solution> findRecent(int howMany){
        List<Solution> result = new ArrayList<>();

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_RECENT_QUERY);
            statement.setInt(1, howMany);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                if (resultSet.getInt("users_id") != 0) {
                    solution.setUsers_id(userDAO.read(resultSet.getInt("users_id")));
                } else {
                    solution.setUsers_id(null);
                }
                // todo finish read other properties

                result.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public Solution[] findAllByUserId(int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID);
            statement.setInt(1, userInput);
            ResultSet resultSet = statement.executeQuery();
            Solution[] solutions = new Solution[0];
            if (resultSet.next() == false) {
                return null;
            } else {
                do {
                    Solution solution = new Solution();
                    solution.setId(resultSet.getInt("id"));
                    solution.setDescription(resultSet.getString("description"));
                    solution.setExercise_id(exerciseDAO.read(resultSet.getInt("exercise_id")));
                    solution.setUsers_id(userDAO.read(resultSet.getInt("users_id")));
                    solution.setCreated(resultSet.getTime("created"));
                    solutions = addToArray(solutions, solution);
                } while (resultSet.next());
            }
            return solutions;
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą");
        }
        return null;
    }

    public Solution[] findAllByExerciseId(int userInput) {
        try (Connection conn = DbUtil.getConnection()) {
            statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_EXERCISE_ID);
            statement.setInt(1, userInput);
            ResultSet resultSet = statement.executeQuery();
            Solution[] solutions = new Solution[0];
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExercise_id(exerciseDAO.read(resultSet.getInt("exercise_id")));
                solutions = addToArray(solutions, solution);
            }
            return solutions;
        } catch (SQLException e) {
            System.out.println("Błąd połączenie z bazą danych");
        }
        return null;
    }


}

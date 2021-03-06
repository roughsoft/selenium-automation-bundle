package com.sysgears.seleniumbundle.core.data

import groovy.util.logging.Slf4j
import org.yaml.snakeyaml.Yaml

/**
 * Provides methods for getting data from data files.
 */
@Slf4j
class DataLoader {

    /**
     * Reads a map from a .yml file.
     *
     * @param file path to the .yml file
     *
     * @return data from the file as a map
     *
     * @throws FileNotFoundException if the file was not found
     */
    static Map readMapFromYml(String file) throws FileNotFoundException {
        readRawDataFromYml(file) as Map
    }

    /**
     * Reads a list from a .yml file.
     *
     * @param file path to the file
     *
     * @return file content as a list
     *
     * @throws FileNotFoundException if the file was not found
     */
    static List readListFromYml(String file) throws FileNotFoundException {
        readRawDataFromYml(file) as List
    }

    /**
     * Reads data as a list from a plain data file (data in the pipe-separated format).
     *
     * @param filePath path to file
     * @param pattern regex pattern to split sets of test data in plain data file
     *
     * @return list of string data sets
     *
     * @throws FileNotFoundException if the file was not found
     */
    static List readListFromPlainDataFile(String filePath, String pattern) throws FileNotFoundException {
        new File(filePath).text.split(pattern)
    }

    /**
     * Reads the raw data from a .yml file.
     *
     * @param filePath a path to a file
     *
     * @return data from the .yml file that can be casted to specific type
     *
     * @throws FileNotFoundException if the file was not found
     */
    static private Object readRawDataFromYml(String filePath) throws FileNotFoundException {
        try {
            new Yaml().load(new File(filePath).newInputStream().text)
        } catch (FileNotFoundException e) {
            log.error("Could not find the data file: $filePath, specify a valid path.", e)
            throw new FileNotFoundException("Could not find the data file: $filePath, specify a valid path.")
        }
    }
}

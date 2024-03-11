export const util = {
  jsonStringToCommaSeparatedKeys: (jsonString: string): string => {
    try {
      const jsonObject = JSON.parse(jsonString);
      const keys = Object.keys(jsonObject);
      const returnString = keys.join(", ");
      return returnString;
    } catch (error) {
      console.error("Error parsing JSON:", error);
      return "";
    }
  },

  jsonStringToCommaSeparatedValues: (jsonString: string): string => {
    try {
      const jsonObject = JSON.parse(jsonString);
      const values = Object.entries(jsonObject).map(([key, value]) => {
        return typeof value === "string" ? `'${value}'` : value;
      });
      const returnString = values.join(", ");
      return returnString;
    } catch (error) {
      console.error("Error parsing JSON:", error);
      return "";
    }
  },

  jsonStringToCommaSeparatedMap: (jsonString: string): string => {
    try {
      const jsonObject = JSON.parse(jsonString);
      const values = Object.entries(jsonObject).map(([key, value]) => {
        return typeof value === "string"
          ? `${key}='${value}'`
          : `${key}=${value}`;
      });
      const returnString = values.join(", ");
      return returnString;
    } catch (error) {
      console.error("Error parsing JSON:", error);
      return "";
    }
  },

  jsonStringToCondition: (jsonString: string): string => {
    try {
      const jsonObject = JSON.parse(jsonString);
      let idString = "";
      for (const key in jsonObject) {
        if (key === "id") {
          if (typeof jsonObject[key] === "string") {
            idString = `id='${jsonObject[key]}'`;
          } else {
            idString = `id=${jsonObject[key]}`;
          }
          break;
        }
      }
      return idString;
    } catch (error) {
      console.error("Error parsing JSON:", error);
      return "";
    }
  },
};

using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;
using System.Configuration;
using MySql.Data.MySqlClient;

namespace CentralWebService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in both code and config file together.
    public class Service1 : IService1
    {
        public string GetStudentId()
        {
            String conString = System.Configuration.ConfigurationManager.ConnectionStrings["MyDatabaseConnectionString"].ConnectionString;
            int studentId = 0;
            using (MySqlConnection cnn = new MySqlConnection(conString))
            {
                cnn.Open();
                String sql = String.Format("SELECT * FROM EMPLOYEE")
            }
        }
       
    }
}

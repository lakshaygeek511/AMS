# Shuffle (Asset Management System) 🛠️

A robust and scalable **Android-based Asset Management System (AMS)** designed to streamline and facilitate efficient IT asset allocation and management. 
Built with **Java** and supported by a powerful **Spring Framework** backend, AMS ensures optimized performance and modular architecture.

# 🔐 Login Details
Use the following credentials for initial access:

Username: VE00ADMIN
Password: Admin@123

## 🚀 Features

### **1. Asset Management**
- **Create Asset Records**: Efficiently add IT assets with detailed specifications.
- **Update Asset Details**: Modify asset details, such as location, user allocation, and status.
- **Track Asset Status**: Real-time monitoring of asset states like:
   - "Allocated," "Available," "Under Maintenance," or "Decommissioned."

### **2. User Management**
- **Role-Based Access**: Distinct roles for Admins and IT Staff.
- **User Assignment**: Allocate or reassign assets to employees seamlessly.

### **3. Interactive Dashboard**
- **Overview Metrics**:
   - Total Assets
   - Allocated Assets
   - Available Assets
   - Assets Under Maintenance
- **Filters & Search**: Easily search and filter asset records based on criteria like asset type, location, and status.

---

## 🛠️ Tech Stack

### **Frontend**
- **Java**: Core logic and architecture.
- **Android SDK**: UI design and implementation.
- **Material Design**: For creating interactive and responsive interfaces.
- **XML**: Defining layout and design components.

### **Backend**
- **Spring Framework**: Backend infrastructure for scalable operations.
- **Hibernate**: ORM for database integration.

### **Database**
- **PostgreSQL**: Centralized database for live production use.

### **Tools & Libraries**
- **Retrofit**: For seamless API integrations.
- **Hilt**: Dependency injection for modular architecture.
- **MVVM Architecture**: Clean and maintainable codebase.
- **Jira**: For bug tracking and task management.

---

## 🎯 Use Cases

1. **Asset Management**
   - Add, update, and delete asset records.
   - Track asset allocation and status updates.

2. **User Management**
   - Role-based permissions (Admins and IT Staff).
   - Efficient assignment and tracking of assets.

3. **Dashboard & Reporting**
   - View asset metrics.
   - Filter and search for specific asset records.

---

## 🎨 UI/UX Design Highlights

- **Clean Interface**: Designed using **Material Design** principles for intuitive navigation.
- **Interactive Dashboard**: Visual representation of key asset data.
- **Modular Layout**: Implemented using **MVVM** architecture for scalability.

![WhatsApp Image 2024-12-18 at 00 33 30_0e68e91b](https://github.com/user-attachments/assets/1679c0ea-b4b8-4ef5-a3cc-2b1fd83449b5)

## 📂 Folder Structure

```plaintext
AMS/
│-- README.md
│-- AMS/
    │-- Models/           # Data Models
    │-- Views/            # UI Components
    │-- ViewModels/       # Business Logic
    │-- Services/         # API Integrations
    │-- Utils/            # Utility Functions
    │-- Database/         # Hibernate Configurations
    │-- Tests/            # Unit Tests and Debugging

# -----------------------------
# Stage 1: Build the Ktor app
# -----------------------------
FROM gradle:8.2.1-jdk17 AS builder

# Set working directory inside container
WORKDIR /app

# Copy project files
COPY . .

# Build the project using Gradle
RUN gradle installDist --no-daemon

# -------------------------------
# Stage 2: Create smaller runtime
# -------------------------------
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the built app from the builder stage
COPY --from=builder /app/build/install/FCMpushServer /app

# Set environment variable for Firebase credentials if needed
# ENV GOOGLE_APPLICATION_CREDENTIALS=/app/service_account_key.json

# Expose the default port
EXPOSE 8080

# Run the Ktor app
CMD ["/app/bin/FCMpushServer"]

package io.kotlintest

import org.reflections.Reflections
import java.util.concurrent.atomic.AtomicBoolean

object Project {

  private var projectConfig: ProjectConfig? = null
  private var executedBefore = AtomicBoolean(false)
  private var executedAfter = AtomicBoolean(false)

  init {
    val configClasses = Reflections("io.kotlintest").getSubTypesOf(ProjectConfig::class.java)
    if (configClasses.size > 1)  {
      val configClassNames = configClasses.map { config -> config.simpleName }
      throw InvalidConfigException("Duplicate GlobalConfig found: $configClassNames")
    }
    projectConfig = configClasses.firstOrNull()?.kotlin?.objectInstance
  }

  fun beforeAll() {
    synchronized(executedBefore) {
      if (!executedBefore.get()) {
        projectConfig?.extensions?.forEach { extension -> extension.beforeAll() }
        projectConfig?.beforeAll()
        executedBefore.set(true)
      }
    }
  }

  fun afterAll() {
    synchronized(executedAfter) {
      if (!executedAfter.get()) {
        projectConfig?.afterAll()
        projectConfig?.extensions?.reversed()?.forEach { extension -> extension.afterAll() }
        executedAfter.set(true)
      }
    }
  }
}
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext

class TL1 {
	
	private Path projectDir
	private Path result
	
	private static  String NL = System.getProperty("line.separator")
	
	TL1() {
		projectDir = Paths.get(RunConfiguration.getProjectDir())
	}
	
	/**
	 * create a file "./tmp/result.txt".
	 * if already present, delete and recreate it.
	 * 
	 * @return
	 */
	@BeforeTestSuite
	def beforeTestSuite(TestSuiteContext testSuiteContext) {
		Path outDir = projectDir.resolve("tmp")
		result = outDir.resolve("result.txt")
		if (Files.exists(result)) {
			Files.delete(result)
		}
		Files.createDirectories(outDir)
		result.toFile() << "STARTED ${testSuiteContext.getTestSuiteId()}${NL}"
	}
	
	@AfterTestCase
	def afterTestCase(TestCaseContext testCaseContext) {
		result.toFile() << "${testCaseContext.getTestCaseId()} ${testCaseContext.getTestCaseStatus()}${NL}"
	}

	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		result.toFile() << "END ${testSuiteContext.getTestSuiteId()}${NL}"
	}
}
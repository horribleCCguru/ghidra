/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package agent.frida;

import java.util.concurrent.CompletableFuture;

import agent.frida.model.impl.FridaModelImpl;
import ghidra.dbg.DebuggerModelFactory;
import ghidra.dbg.DebuggerObjectModel;
import ghidra.dbg.util.ConfigurableFactory.FactoryDescription;
import ghidra.program.model.listing.Program;

@FactoryDescription(
	brief = "PROTOTYPE: Frida",
	htmlDetails = """
			Connect to Frida.
			This is an experimental connector. Use at your own risk.
			This will access the native API, which may put Ghidra's JVM at risk.""")
public class FridaInJvmDebuggerModelFactory implements DebuggerModelFactory {

	@Override
	public CompletableFuture<? extends DebuggerObjectModel> build() {
		FridaModelImpl model = new FridaModelImpl();
		return model.startFrida(new String[] {}).thenApply(__ -> model);
	}

	@Override
	public int getPriority(Program program) {
		String osname = System.getProperty("os.name").toLowerCase();
		if (!(osname.contains("mac os x") || osname.contains("linux") ||
			osname.contains("windows"))) {
			return -1;
		}
		if (program != null) {
			String exe = program.getExecutablePath();
			if (exe == null || exe.isBlank()) {
				return -1;
			}
		}
		return 30;
	}
}

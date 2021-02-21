package com.diego.shifts.viewmodels

import com.diego.core.PermissionException
import com.diego.shifts.contract.AsyncUseCase
import com.diego.shifts.contract.Shift
import com.diego.shifts.contract.UseCase
import com.diego.shifts.data.ShiftUiModel
import com.diego.shifts.others.InstantExecutorExtension
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class, InstantExecutorExtension::class)
class ShiftsViewModelTest {

  @MockK
  private lateinit var getShiftsUseCase: AsyncUseCase<Unit, List<Shift>>

  @MockK
  private lateinit var startShiftUseCase: AsyncUseCase<Unit, String>

  @MockK
  private lateinit var endShiftUseCase: AsyncUseCase<Unit, String>

  @MockK
  private lateinit var mapShiftToUiModelUseCase: UseCase<Shift, ShiftUiModel>

  private lateinit var viewModel: ShiftsViewModel

  private val dispatcherIO = TestCoroutineDispatcher()

  @BeforeEach
  fun setup() {
    coEvery { getShiftsUseCase.execute(Unit) } returns emptyList()
  }

  private fun initViewModel() {
    viewModel = ShiftsViewModel(
      getShiftsUseCase,
      startShiftUseCase,
      endShiftUseCase,
      mapShiftToUiModelUseCase,
      dispatcherIO
    )
  }

  @Test
  fun `fetch shifts and hide loading`() {
    val shifts = listOf<Shift>(mockk())
    val shiftUiModel = mockk<ShiftUiModel>()
    val expected = listOf(shiftUiModel)
    coEvery { getShiftsUseCase.execute(Unit) } returns shifts
    every { mapShiftToUiModelUseCase.execute(any()) } returns shiftUiModel

    runBlockingTest {
      initViewModel()

      assertEquals(expected, viewModel.data.value?.shifts)
      assertEquals(false, viewModel.data.value?.loading)
    }
  }

  @Test
  fun `start shift shows correct message`() {
    val message = "OK"
    coEvery { startShiftUseCase.execute(Unit) } returns message

    runBlockingTest {
      initViewModel()
      viewModel.startShift()
      assertTrue(viewModel.news.value is ShiftNews.MessageNews)
      assertEquals(message, (viewModel.news.value as ShiftNews.MessageNews).message)
    }
  }

  @Test
  fun `start shift shows permission exception`() {
    coEvery { startShiftUseCase.execute(Unit) } throws PermissionException

    runBlockingTest {
      initViewModel()
      viewModel.startShift()
      assertTrue(viewModel.news.value is ShiftNews.RequestPermissionNews)
    }
  }

  @Test
  fun `start shift shows message if unknown exception`() {
    val message = "error"
    coEvery { startShiftUseCase.execute(Unit) } throws Exception(message)

    runBlockingTest {
      initViewModel()
      viewModel.startShift()
      assertTrue(viewModel.news.value is ShiftNews.MessageNews)
      assertEquals(message, (viewModel.news.value as ShiftNews.MessageNews).message)
    }
  }

  @Test
  fun `end shift shows correct message`() {
    val message = "OK"
    coEvery { endShiftUseCase.execute(Unit) } returns message

    runBlockingTest {
      initViewModel()
      viewModel.endShift()
      assertTrue(viewModel.news.value is ShiftNews.MessageNews)
      assertEquals(message, (viewModel.news.value as ShiftNews.MessageNews).message)
    }
  }

  @Test
  fun `end shift shows permission exception`() {
    coEvery { endShiftUseCase.execute(Unit) } throws PermissionException

    runBlockingTest {
      initViewModel()
      viewModel.endShift()
      assertTrue(viewModel.news.value is ShiftNews.RequestPermissionNews)
    }
  }

  @Test
  fun `end shift shows message if unknown exception`() {
    val message = "error"
    coEvery { endShiftUseCase.execute(Unit) } throws Exception(message)

    runBlockingTest {
      initViewModel()
      viewModel.endShift()
      assertTrue(viewModel.news.value is ShiftNews.MessageNews)
      assertEquals(message, (viewModel.news.value as ShiftNews.MessageNews).message)
    }
  }
}